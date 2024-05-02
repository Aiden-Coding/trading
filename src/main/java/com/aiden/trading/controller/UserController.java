package com.aiden.trading.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.aiden.trading.dto.Result;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@Tag(name = "认证")
@RequestMapping("/user")
public class UserController {


    // 查询登录状态，浏览器访问： http://localhost:8081/user/isLogin
    @GetMapping("getUserInfo")
    public Result<?> getUserInfo() {
        Map<String,String> ret = new HashMap<>();
        ret.put("username","hell");
        ret.put("realName","helwl");
        return Result.data(ret);
    }

    // 查询 Token 信息  ---- http://localhost:8081/acc/tokenInfo
    @GetMapping("tokenInfo")
    public SaResult tokenInfo() {
        return SaResult.data(StpUtil.getTokenInfo());
    }

    // 测试注销  ---- http://localhost:8081/acc/logout
    @GetMapping("logout")
    public SaResult logout() {
        StpUtil.logout();
        return SaResult.ok();
    }

}
