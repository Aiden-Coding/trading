package com.aiden.trading.controller;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.aiden.trading.dto.Result;
import com.aiden.trading.dto.user.req.LoginReq;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Tag(name = "认证")
public class UserController {
    // 测试登录，浏览器访问： http://localhost:8081/user/doLogin?username=zhang&password=123456
    @PostMapping("doLogin")
    public Result<?> doLogin(@RequestBody LoginReq loginReq) {
        // 此处仅作模拟示例，真实项目需要从数据库中查询数据进行比对
        if("vben".equals(loginReq.getUsername()) && "123456".equals(loginReq.getPassword())) {
            StpUtil.login(10001);
            // 第2步，获取 Token  相关参数
            SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
            return Result.data(tokenInfo);
        }


        return Result.error("登录失败");
    }

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
