package com.spring.security.spring.security.example.bCryptPasswordEncoder;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class BCryptPasswordEncoderApplication {

    public static void main(String[] args) {
        SpringApplication.run(BCryptPasswordEncoderApplication.class, args);
    }
}
