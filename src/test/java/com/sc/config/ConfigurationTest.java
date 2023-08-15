package com.sc.config;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(SpringExtension.class)
 class ConfigurationTest {

    @Test
     void testGetterSetter(){
        Configuration configuration = new Configuration();
        configuration.setUsername(null);
        configuration.setPassword(null);
        configuration.setCredentials_json(null);
        assertNull(configuration.getUsername());
        assertNull(configuration.getPassword() );
        assertNull(configuration.getCredentials_json());
    }
}
