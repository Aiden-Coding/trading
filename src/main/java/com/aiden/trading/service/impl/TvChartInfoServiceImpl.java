package com.aiden.trading.service.impl;

import com.aiden.trading.dao.TvChartInfoDao;
import com.aiden.trading.dto.tradingview.ChartInfo;
import com.aiden.trading.entity.TvChartInfo;
import com.aiden.trading.service.ITvChartInfoService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * 布局 服务实现类
 * </p>
 *
 * @author zd
 * @since 2024-03-30 21:05:46
 */
@Service
public class TvChartInfoServiceImpl extends ServiceImpl<TvChartInfoDao, TvChartInfo> implements ITvChartInfoService {

    @Override
    public ChartInfo getChartInfoById(Integer chart, String user, String client) {
        LambdaQueryWrapper<TvChartInfo> queryWrapper = new LambdaQueryWrapper<>() ;
        queryWrapper.eq(TvChartInfo::getId, chart);
        queryWrapper.eq(TvChartInfo::getUser, user);
        queryWrapper.eq(TvChartInfo::getClient, client);
        TvChartInfo tvStudyTemplateInfo = baseMapper.selectOne(queryWrapper);
        if (Objects.nonNull(tvStudyTemplateInfo)) {
            ChartInfo ret = new ChartInfo();
            ret.setName(tvStudyTemplateInfo.getName());
            ret.setContent(Arrays.toString(tvStudyTemplateInfo.getContent()));
            ret.setId(tvStudyTemplateInfo.getId());
            ret.setTimestamp(tvStudyTemplateInfo.getUpdateTime().getTime()/1000);
            return ret;
        }
        return null;
    }

    @Override
    public List<Map<String, Object>> getChartInfos(String user, String client) {
        LambdaQueryWrapper<TvChartInfo> queryWrapper = new LambdaQueryWrapper<>() ;
        queryWrapper.select(TvChartInfo::getId)
                .select(TvChartInfo::getName)
                .select(TvChartInfo::getResolution)
                .select(TvChartInfo::getUpdateTime)
                .select(TvChartInfo::getSymbol);
        queryWrapper.eq(TvChartInfo::getUser, user);
        queryWrapper.eq(TvChartInfo::getClient, client);
        return baseMapper.selectMaps(queryWrapper);
    }
}
