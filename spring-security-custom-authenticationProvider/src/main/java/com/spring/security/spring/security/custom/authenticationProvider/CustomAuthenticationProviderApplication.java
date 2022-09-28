package com.spring.security.spring.security.custom.authenticationProvider;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class CustomAuthenticationProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomAuthenticationProviderApplication.class, args);
    }
}
