package com.springboot.security.hash.app.config;

import com.springboot.security.hash.app.model.HashConfigProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.SecureRandom;

@Configuration
public class PasswordEncoderConfig {

    @Autowired
    private HashConfigProperties hashConfigProperties;

    @Bean
    public PasswordEncoder passwordEncoder() {
        SecureRandom random = new SecureRandom();
        return new BCryptPasswordEncoder(hashConfigProperties.getBcrypt().getStrength(), random);
    }

}
