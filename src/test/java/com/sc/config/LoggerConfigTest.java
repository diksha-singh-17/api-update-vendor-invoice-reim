package com.sc.config;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class LoggerConfigTest {
    LoggerConfig loggerConfig;
    @BeforeEach
    public void before() {
        loggerConfig =new LoggerConfig();
    }

    @Test
    void testGetLoggerUtility(){

        loggerConfig.getLoggerUtility();
        Assertions.assertNotNull(loggerConfig);
    }

}
