package com.aiden.trading.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 配置文件
 * @author 公众号:知了一笑
 * @since 2023-07-11 19:09
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     * 接口文档配置
     */
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info().title("【boot-quartz】")
                        .description("Quartz接口文档-2023-07-11")
                        .version("1.0.0"));
    }
}