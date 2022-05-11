package com.springboot.security.hash.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class BcryptService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String hashBcrypt(String data) {
        return this.passwordEncoder.encode(data);
    }

    public boolean isBcryptMatch(String data, String hashedData) {
        return this.passwordEncoder.matches(data, hashedData);
    }

}
