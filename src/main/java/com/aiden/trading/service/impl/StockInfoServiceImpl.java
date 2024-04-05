package com.aiden.trading.service.impl;

import com.aiden.trading.config.AKshareApi;
import com.aiden.trading.dao.StockInfoDao;
import com.aiden.trading.dto.AkResult;
import com.aiden.trading.dto.akshare.AkShareReq;
import com.aiden.trading.dto.stock.SymbolResp;
import com.aiden.trading.dto.stock.req.DataDayWeekYearReq;
import com.aiden.trading.entity.StockInfo;
import com.aiden.trading.service.IStockInfoService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.*;

/**
 * <p>
 * 股票信息 服务实现类
 * </p>
 *
 * @author zd
 * @since 2024-04-05 14:10:59
 */
@Slf4j
@Service
public class StockInfoServiceImpl extends ServiceImpl<StockInfoDao, StockInfo> implements IStockInfoService {

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;
    @Resource
    private AKshareApi aKshareApi;

    @Override
    public List<SymbolResp> searchSymbols(String userInput, String exchange, String symbolType) {
        List<SymbolResp> ret = new ArrayList<>();
        log.info("param {} {} {}", userInput, symbolType, exchange);
        LambdaQueryWrapper<StockInfo> queryWrapper = new LambdaQueryWrapper<>();
        List<StockInfo> list = baseMapper.selectList(queryWrapper);
        if (CollectionUtils.isNotEmpty(list)) {
            list.forEach(x -> {
                SymbolResp it = new SymbolResp();
                it.setSymbol(x.getCode());
                it.setFullName(x.getExchangeCode() + ":" + x.getCode() + "/" + x.getCurrency());
                it.setDescription(x.getName());
                it.setType("stock");
                it.setExchangeCode(x.getExchangeCode());
                ret.add(it);
            });
        }
        return ret;
    }

    @Override
    public AkResult<?> getDayWeekYearData(DataDayWeekYearReq dataDayWeekYearReq) {
        if (Objects.nonNull(dataDayWeekYearReq.getFrom()) && Objects.nonNull(dataDayWeekYearReq.getTo())) {
            // 将时间戳转换为 Instant 对象
            Instant instant = Instant.ofEpochMilli(dataDayWeekYearReq.getFrom() * 1000L);
            Instant instant2 = Instant.ofEpochMilli(dataDayWeekYearReq.getTo() * 1000L);
            // 获取当前系统默认的时区
            ZoneId defaultZone = ZoneId.systemDefault();
            // 将 Instant 对象转换为 LocalDateTime 对象，使用系统默认的时区
            LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, defaultZone);
            LocalDateTime localDateTime2 = LocalDateTime.ofInstant(instant2, defaultZone);

            long daysBetween = ChronoUnit.DAYS.between(localDateTime, localDateTime2);
            if (daysBetween == 1) {
                dataDayWeekYearReq.setFrom(dataDayWeekYearReq.getFrom() - 24*60*60*3L);
            }

        }
        AkShareReq akShareReq = new AkShareReq();
        akShareReq.setMethod("stock_zh_a_hist");
        Map<String, Object> args = new HashMap<>();
        akShareReq.setArgs(args);
        args.put("symbol", dataDayWeekYearReq.getSymbol());
        //period='daily'; choice of {'daily', 'weekly', 'monthly'}
        String period = "daily";
        if (Objects.equals("1D", dataDayWeekYearReq.getResolution())) {
            period = "daily";
        } else if (Objects.equals("1W", dataDayWeekYearReq.getResolution())) {
            period = "weekly";
        } else if (Objects.equals("1M", dataDayWeekYearReq.getResolution())) {
            period = "monthly";
        }
        args.put("period", period);
//        start_date="20210101"
        args.put("start_date",  DateFormatUtils.format(new Date(dataDayWeekYearReq.getFrom()*1000L),"yyyyMMdd"));
//        end_date="20210601"
        args.put("end_date", DateFormatUtils.format(new Date(dataDayWeekYearReq.getTo()*1000L),"yyyyMMdd"));
//        默认 adjust="", 则返回未复权的数据; adjust="qfq" 则返回前复权的数据, adjust="hfq" 则返回后复权的数据,
//        args.put("adjust", "主板A股");
        AkResult<?> ret = aKshareApi.pyMethod(akShareReq);
        return ret;
    }

    @Override
    public void tradeDate() {
        AkShareReq akShareReq = new AkShareReq();
        akShareReq.setMethod("tool_trade_date_hist_sina");
        AkResult<List<Map<String,Long>>> ret = (AkResult<List<Map<String, Long>>>) aKshareApi.pyMethod(akShareReq);
        if (Objects.nonNull(ret) && Objects.equals(0,ret.getCode())) {
            for (Map<String,Long> da: ret.getData()) {
                redisTemplate.opsForSet().add("trade_date",da.get("trade_date")/1000);
            }
        }
    }
}
