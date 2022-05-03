package com.springboot.security.hash.app.model;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "hash")
public class HashConfigProperties {

    private Sha256 sha256;
    private Sha512 sha512;

    private Pbkdf2 pbkdf2;

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

    public Pbkdf2 getPbkdf2() {
        return pbkdf2;
    }

    public void setPbkdf2(Pbkdf2 pbkdf2) {
        this.pbkdf2 = pbkdf2;
    }
}
