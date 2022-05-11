package com.springboot.security.hash.app.service;

import com.springboot.security.hash.app.model.HashConfigProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class BcryptService {


    @Autowired
    private HashConfigProperties hashConfigProperties;
    public String hashBcrypt(String data) {
        PasswordEncoder passwordEncoder = this.buildPasswordEncoder();
        return passwordEncoder.encode(data);
    }

    public boolean isBcryptMatch(String data, String hashedData) {
        PasswordEncoder passwordEncoder = this.buildPasswordEncoder();
        return passwordEncoder.matches(data, hashedData);
    }

    private PasswordEncoder buildPasswordEncoder() {
        SecureRandom random = new SecureRandom();
        return new BCryptPasswordEncoder(hashConfigProperties.getBcrypt().getStrength(), random);
    }
}
