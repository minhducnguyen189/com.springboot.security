package com.springboot.security.custom.basic.filter.auth.config;

import com.springboot.security.custom.basic.filter.auth.model.BasicAuthTokenProvider;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
public class ConfigurationPropertyConfig {

    @Bean
    @ConfigurationProperties(prefix = "basic.auth")
    public BasicAuthTokenProvider basicAuthTokenProvider() {
        return new BasicAuthTokenProvider();
    }

}
