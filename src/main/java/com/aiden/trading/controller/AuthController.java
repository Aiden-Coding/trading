package com.aiden.trading.controller;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.aiden.trading.dto.Result;
import com.aiden.trading.dto.user.req.LoginReq;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Tag(name = "认证")
public class AuthController {
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
    @GetMapping("isLogin")
    public String isLogin() {
        return "当前会话是否登录：" + StpUtil.isLogin();
    }

    // 测试注销  ---- http://localhost:8081/acc/logout
    @GetMapping("doLogout")
    public SaResult logout() {
        StpUtil.logout();
        return SaResult.ok();
    }

}
