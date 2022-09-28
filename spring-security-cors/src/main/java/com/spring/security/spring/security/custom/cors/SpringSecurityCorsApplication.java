package com.spring.security.spring.security.custom.cors;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class SpringSecurityCorsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityCorsApplication.class, args);
    }
}
