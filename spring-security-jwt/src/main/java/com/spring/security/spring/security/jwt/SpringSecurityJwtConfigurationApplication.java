package com.spring.security.spring.security.jwt;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class SpringSecurityJwtConfigurationApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityJwtConfigurationApplication.class, args);
    }
}
