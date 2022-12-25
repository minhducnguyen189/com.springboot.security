package com.springboot.security.spring.security.oauth.client.server.password.grant.type;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class SpringSecurityOauthPasswordGrantTypeClientServer {
    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityOauthPasswordGrantTypeClientServer.class, args);
    }
}