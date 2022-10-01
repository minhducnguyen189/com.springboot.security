package com.spring.security.spring.security.configure.authorities;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class SpringSecurityAuthorityConfigurationApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityAuthorityConfigurationApplication.class, args);
    }
}
