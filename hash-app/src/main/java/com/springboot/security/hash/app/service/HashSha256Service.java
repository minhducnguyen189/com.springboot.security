package com.springboot.security.hash.app.service;

import com.springboot.security.hash.app.model.HashConfigProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

@Service
public class HashSha256Service {

    private static final String HASH_ALGORITHM_SHA_256 = "SHA-256";

    @Autowired
    private HashConfigProperties hashConfigProperties;

    public String hashSHA256(String data) {
        try {
            String dataWithSalt = hashConfigProperties.getSha256().getSalt().concat(data);
            MessageDigest messageDigest = MessageDigest.getInstance(HASH_ALGORITHM_SHA_256);
            byte[] hash = messageDigest.digest(dataWithSalt.getBytes(StandardCharsets.UTF_8));
            return DatatypeConverter.printHexBinary(hash);
        } catch (Exception ex) {
            throw new RuntimeException("Can not hash Data", ex);
        }
    }

    public boolean isSHA256Match(String data, String hashData) {
        String reHashData = this.hashSHA256(data);
        return reHashData.equals(hashData);
    }

}
