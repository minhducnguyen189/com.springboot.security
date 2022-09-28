package com.spring.security.spring.security.custom.userDetailsService;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class CustomUserDetailsServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomUserDetailsServiceApplication.class, args);
    }
}
