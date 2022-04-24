package com.springboot.security.symmetric.encrypt.decrypt.app.service;

import com.springboot.security.symmetric.encrypt.decrypt.app.model.Aes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Objects;

@Service
public class Aes256Service {

    private static final String CIPHER_TRANSFORMATION = "AES/CBC/PKCS5PADDING";
    private static final String ENCRYPTION_ALGORITHM = "AES";

    @Autowired
    private Aes aes;

    public String createEncryptKey() {
        try {
            SecureRandom secureRandom = new SecureRandom();
            KeyGenerator keyGenerator = KeyGenerator.getInstance(ENCRYPTION_ALGORITHM);
            keyGenerator.init(256, secureRandom);
            SecretKey key = keyGenerator.generateKey();
            return DatatypeConverter.printHexBinary(key.getEncoded());
        } catch (Exception ex) {
            throw new RuntimeException("Can not generate Secret Key", ex);
        }
    }

    public String encryptData(String data) {
        try {
            Cipher cipher = Cipher.getInstance(CIPHER_TRANSFORMATION);
            byte[] key = DatatypeConverter.parseHexBinary(aes.getSecret());
            SecretKeySpec secretKeySpec = new SecretKeySpec(key, ENCRYPTION_ALGORITHM);
            if (Objects.isNull(aes.getIvSecret())) {
                byte[] ivParameterSpecKey = this.generateIvParameterSpec();
                IvParameterSpec ivParameterSpec = new IvParameterSpec(ivParameterSpecKey);
                cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
                byte[] encryptedData = cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));
                String ivParameterSpecKeyString = DatatypeConverter.printHexBinary(ivParameterSpecKey);
                String encryptedDataString = DatatypeConverter.printHexBinary(encryptedData);
                return ivParameterSpecKeyString.concat(encryptedDataString);
            }
            IvParameterSpec ivParameterSpec = new IvParameterSpec(aes.getIvSecret().getBytes(StandardCharsets.UTF_8));
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
            byte[] encryptedData = cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));
            return DatatypeConverter.printHexBinary(encryptedData);
        } catch (Exception ex) {
            throw new RuntimeException("Can not encrypt Data", ex);
        }
    }

    public String decryptData(String data) {
        try {
            Cipher cipher = Cipher.getInstance(CIPHER_TRANSFORMATION);
            byte[] key = DatatypeConverter.parseHexBinary(aes.getSecret());
            SecretKeySpec secretKeySpec = new SecretKeySpec(key, ENCRYPTION_ALGORITHM);
            byte[] dataBytes = DatatypeConverter.parseHexBinary(data);
            if (Objects.isNull(aes.getIvSecret()) && dataBytes.length > 16) {
                byte[] ivParameterSpecKey = this.getIvParameterSpecKey(dataBytes);
                byte[] payload = Arrays.copyOfRange(dataBytes, 16, dataBytes.length);
                IvParameterSpec ivParameterSpec = new IvParameterSpec(ivParameterSpecKey);
                cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
                byte[] decryptedData = cipher.doFinal(payload);
                return new String(decryptedData, StandardCharsets.UTF_8);
            }
            IvParameterSpec ivParameterSpec = new IvParameterSpec(aes.getIvSecret().getBytes(StandardCharsets.UTF_8));
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
            byte[] decryptedData = cipher.doFinal(dataBytes);
            return new String(decryptedData, StandardCharsets.UTF_8);
        } catch (Exception ex) {
            throw new RuntimeException("Can not decrypt Data", ex);
        }
    }

    private byte[] generateIvParameterSpec() {
        byte[] iv = new byte[16];
        SecureRandom random = new SecureRandom();
        random.nextBytes(iv);
        return iv;
    }

    private byte[] getIvParameterSpecKey(byte[] dataBytes) {
        return Arrays.copyOfRange(dataBytes, 0, 16);
    }

}
