package com.springboot.security.symmetric.encrypt.decrypt.app.model;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "aes")
public class Aes {

    private String secret;
    private String ivSecret;

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getIvSecret() {
        return ivSecret;
    }

    public void setIvSecret(String ivSecret) {
        this.ivSecret = ivSecret;
    }
}
