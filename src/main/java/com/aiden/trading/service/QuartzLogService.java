package com.aiden.trading.service;

import com.aiden.trading.entity.QuartzLog;
import com.aiden.trading.mapper.QuartzLogMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 *
 * @author 公众号:知了一笑
 * @since 2023-07-26 11:02
 */
@Service("quartzLogService")
public class QuartzLogService {

    @Resource
    private QuartzLogMapper quartzLogMapper ;

    public Integer insert(QuartzLog quartzLog) {
        return quartzLogMapper.insert(quartzLog);
    }
}
