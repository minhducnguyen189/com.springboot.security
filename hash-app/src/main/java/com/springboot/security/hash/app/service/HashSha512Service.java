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
        byte[] hash = this.hash(data);
        return DatatypeConverter.printHexBinary(hash);
    }

    public boolean isSHA512Match(String data, String hashedData) {
        byte[] digested = DatatypeConverter.parseHexBinary(hashedData);
        byte[] reHashData = this.hash(data);
        return MessageDigest.isEqual(digested, reHashData);
    }

    private byte[] hash(String data) {
        try {
            String dataWithSalt = hashConfigProperties.getSha512().getSalt().concat(data);
            MessageDigest messageDigest = MessageDigest.getInstance(HASH_ALGORITHM_512);
            return messageDigest.digest(dataWithSalt.getBytes(StandardCharsets.UTF_8));
        } catch (Exception ex) {
            throw new RuntimeException("Can not hash Data", ex);
        }
    }

}
