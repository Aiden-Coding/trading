package com.aiden.trading.handler;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.util.SaResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    // 全局异常拦截
    @ExceptionHandler(NotLoginException.class)
    public SaResult handlerException(Exception e) {
        log.error(e.getMessage(),e);
        return SaResult.error(e.getMessage());
    }
}
