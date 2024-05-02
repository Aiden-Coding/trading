package com.aiden.trading.controller;

import com.aiden.trading.dto.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 用户 前端控制器
 * </p>
 *
 * @author zd
 * @since 2024-05-02 13:56:10
 */
@RestController
@RequestMapping("/user")
public class UserInfoController {


    // 查询登录状态，浏览器访问： http://localhost:8081/user/isLogin
    @GetMapping("getUserInfo")
    public Result<?> getUserInfo() {
        Map<String,String> ret = new HashMap<>();
        ret.put("username","hell");
        ret.put("realName","helwl");
        return Result.data(ret);
    }
}
