package com.springboot.security.hash.app.service;

import com.springboot.security.hash.app.model.HashConfigProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

@Service
public class Pbkdf2Service {

    private static final String HASH_ALGORITHM_PBKDF2_512 = SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA512.name();

    @Autowired
    private HashConfigProperties hashConfigProperties;

    public String hashPbkdf2(String data) {
        try {
            byte[] hash = this.hash(data);
            return DatatypeConverter.printHexBinary(hash);
        } catch (Exception ex) {
            throw new RuntimeException("Can not hash Data", ex);
        }
    }

    public boolean isPbkdf2Match(String data, String hashedData) {
        byte[] digested = DatatypeConverter.parseHexBinary(hashedData);
        byte[] reHashData = this.hash(data);
        return MessageDigest.isEqual(digested, reHashData);
    }

    private byte[] hash(String data) {
        try {
            PBEKeySpec pbeKeySpec = new PBEKeySpec(data.toCharArray(),
                hashConfigProperties.getPbkdf2().getSalt().getBytes(StandardCharsets.UTF_8),
                hashConfigProperties.getPbkdf2().getIterations(),
                hashConfigProperties.getPbkdf2().getHashWidth());
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(HASH_ALGORITHM_PBKDF2_512);
            return secretKeyFactory.generateSecret(pbeKeySpec).getEncoded();
        } catch (Exception ex) {
            throw new RuntimeException("Can not hash Data", ex);
        }
    }

    public enum SecretKeyFactoryAlgorithm {

        PBKDF2WithHmacSHA1, PBKDF2WithHmacSHA256, PBKDF2WithHmacSHA512

    }

}
