package com.springboot.security.hash.app.service;

import com.springboot.security.hash.app.model.HashConfigProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

@Service
public class HashSha512Service {

    private static final String HASH_ALGORITHM_512 = "SHA-512";

    @Autowired
    private HashConfigProperties hashConfigProperties;

    public String hashSHA512(String data) {
        try {
            String dataWithSalt = hashConfigProperties.getSha512().getSalt().concat(data);
            MessageDigest messageDigest = MessageDigest.getInstance(HASH_ALGORITHM_512);
            byte[] hash = messageDigest.digest(dataWithSalt.getBytes(StandardCharsets.UTF_8));
            return DatatypeConverter.printHexBinary(hash);
        } catch (Exception ex) {
            throw new RuntimeException("Can not hash Data", ex);
        }
    }

    public boolean isSHA512Match(String data, String hashData) {
        String reHashData = this.hashSHA512(data);
        return reHashData.equals(hashData);
    }

}
