package com.aiden.trading.config;

import com.aiden.trading.dto.Result;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

@HttpExchange
public interface AKshareApi {

    @PostExchange("/api")
    Result pyMethod();
}
