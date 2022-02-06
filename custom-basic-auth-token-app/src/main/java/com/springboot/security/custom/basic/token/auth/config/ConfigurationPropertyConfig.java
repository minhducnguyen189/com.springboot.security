package com.springboot.security.custom.basic.token.auth.config;

import com.springboot.security.custom.basic.token.auth.model.BasicAuthToken;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
public class ConfigurationPropertyConfig {


    @Bean
    @ConfigurationProperties(prefix = "basic.auth")
    public BasicAuthToken basicAuthToken() {
        return new BasicAuthToken();
    }

}
