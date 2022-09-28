package com.spring.security.spring.security.custom.csrf;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class SpringSecurityCsrfApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityCsrfApplication.class, args);
    }
}
