package com.example.webflux1.config;

import dev.miku.r2dbc.mysql.MySqlConnectionConfiguration;
import dev.miku.r2dbc.mysql.MySqlConnectionFactory;
import io.r2dbc.spi.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author su
 * @Date 2021/2/14 21:59
 */
@Configuration
public class DataSourceConfig {
    @Bean
    ConnectionFactory connectionFactory() {
        return MySqlConnectionFactory.from(MySqlConnectionConfiguration.builder()
                .host("xxx.xxx.xxx.xxx")
                .port(3306)
                .username("root")
                .password("xxxxxx")
                .database("xxxx")
                // 额外的其它非必选参数省略
                .build());
    }
}
