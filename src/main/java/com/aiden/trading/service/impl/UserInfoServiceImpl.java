package com.aiden.trading.service.impl;

import com.aiden.trading.entity.UserInfo;
import com.aiden.trading.dao.UserInfoDao;
import com.aiden.trading.service.IUserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户 服务实现类
 * </p>
 *
 * @author zd
 * @since 2024-05-02 13:56:10
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoDao, UserInfo> implements IUserInfoService {

}
