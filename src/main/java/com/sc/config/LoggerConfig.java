package com.sc.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sc.util.LoggerUtility;


@Configuration
@ConfigurationProperties
public class LoggerConfig {

    @Bean
    public LoggerUtility getLoggerUtility() {
        return new LoggerUtility();
    }
}
