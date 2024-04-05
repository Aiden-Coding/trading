package com.aiden.trading.service.impl;

import com.aiden.trading.entity.StockInfo;
import com.aiden.trading.dao.StockInfoDao;
import com.aiden.trading.service.IStockInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 股票信息 服务实现类
 * </p>
 *
 * @author zd
 * @since 2024-04-05 14:10:59
 */
@Service
public class StockInfoServiceImpl extends ServiceImpl<StockInfoDao, StockInfo> implements IStockInfoService {

}
