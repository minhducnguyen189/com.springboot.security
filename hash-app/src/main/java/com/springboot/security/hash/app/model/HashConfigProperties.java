package com.springboot.security.hash.app.model;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "hash")
public class HashConfigProperties {

    private Sha256 sha256;
    private Sha512 sha512;

    public Sha256 getSha256() {
        return sha256;
    }

    public void setSha256(Sha256 sha256) {
        this.sha256 = sha256;
    }

    public Sha512 getSha512() {
        return sha512;
    }

    public void setSha512(Sha512 sha512) {
        this.sha512 = sha512;
    }
}
