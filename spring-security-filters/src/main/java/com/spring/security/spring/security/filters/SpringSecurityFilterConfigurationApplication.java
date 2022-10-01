package com.spring.security.spring.security.filters;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class SpringSecurityFilterConfigurationApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityFilterConfigurationApplication.class, args);
    }
}
