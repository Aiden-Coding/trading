package com.aiden.trading.job;

import com.aiden.trading.config.AKshareApi;
import com.aiden.trading.dto.AkResult;
import com.aiden.trading.dto.akshare.AkShareReq;
import com.aiden.trading.entity.StockGn;
import com.aiden.trading.service.IStockGnItemService;
import com.aiden.trading.service.IStockGnService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;


@Slf4j
@Component("syncGnChinaStockJob")
public class SyncGnChinaStockJob implements JobService {

    @Resource
    private AKshareApi aKshareApi;
    @Resource
    private IStockGnService stockGnService;
    @Resource
    private IStockGnItemService stockGnItemService;

    @Override
    public void run(String params) {
        log.info("\n ======== \n syncChinaStockJob-job-params:{} \n ========", params);
        AkShareReq akShareReq = new AkShareReq();
        akShareReq.setMethod("stock_board_concept_name_ths");
        Map<String, Object> args = new HashMap<>();
        akShareReq.setArgs(args);
        @SuppressWarnings("unchecked")
        AkResult<List<Map<String, Object>>> ret = (AkResult<List<Map<String, Object>>>) aKshareApi.pyMethod(akShareReq);
        if (Objects.equals(ret.getCode(), 0)) {
            if (CollectionUtils.isNotEmpty(ret.getData())) {
                for (Map<String, Object> dataItem : ret.getData()) {
                    try {
                        LambdaQueryWrapper<StockGn> queryWrapper = new LambdaQueryWrapper<>();
                        queryWrapper.eq(StockGn::getExCode, (String) dataItem.get("代码"));
                        queryWrapper.eq(StockGn::getSource, "THS");
                        StockGn stockInfo = new StockGn();
                        stockInfo.setExCode((String) dataItem.get("代码"));
                        stockInfo.setName((String) dataItem.get("概念名称"));
                        if (Objects.nonNull(dataItem.get("网址"))) {
                            stockInfo.setUrl((String) dataItem.get("网址"));
                        }
                        stockInfo.setSource("THS");

                        if (Objects.nonNull(dataItem.get("日期"))) {
                            Date date = new Date((Long) dataItem.get("日期"));
                            // 将时间戳转换为 Instant 对象
                            Instant instant = Instant.ofEpochMilli(date.getTime());
                            // 获取当前系统默认的时区
                            ZoneId defaultZone = ZoneId.systemDefault();
                            // 将 Instant 对象转换为 LocalDateTime 对象，使用系统默认的时区
                            LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, defaultZone);
                            stockInfo.setListDate(localDateTime);
                        }
                        StockGn stock = stockGnService.getOne(queryWrapper);
                        if (Objects.nonNull(stock)) {
                            stockInfo.setId(stock.getId());
                            stockInfo.setCode(stock.getCode());
                            stockGnService.updateById(stockInfo);
                        } else {
                            stockGnService.save(stockInfo);
                        }
                    } catch (Exception e) {
                        log.error("dataItem:{}",dataItem.get("代码"),e);
                    }
                }
            }
        }
    }
}
