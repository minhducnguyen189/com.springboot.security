package com.springboot.security.custom.basic.spring.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableJpaRepositories
@EnableWebSecurity(debug = true)
public class CustomDefaultSpringSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomDefaultSpringSecurityApplication.class, args);
    }

}
