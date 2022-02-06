package com.springboot.security.custom.basic.auth.service;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.springboot.security.custom.basic.auth.entity.Role;
import com.springboot.security.custom.basic.auth.entity.UserEntity;
import com.springboot.security.custom.basic.auth.model.UserDto;
import com.springboot.security.custom.basic.auth.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Service
@AllArgsConstructor(onConstructor = @__({@Autowired}))
public class UserService {

    private final UserRepository userRepository;

    private static final String SECURE_RANDOM_ALGORITHMS = "SHA1PRNG";


    public UUID createUser(UserDto user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(user.getEmail());
        userEntity.setPassword(this.hashPassword(user.getPassword()));
        userEntity.setCreatedDate(LocalDateTime.now());
        userEntity.setRole(user.getRole());
        return this.userRepository.save(userEntity).getId();
    }

    public UserDto getUserByEmail(String email) {
        UserEntity userEntity = this.userRepository.findByUsername(email);
        if (Objects.isNull(userEntity)) {
            throw new RuntimeException("User not found!");
        }
        UserDto userDto = new UserDto();
        userDto.setEmail(userEntity.getUsername());
        userDto.setPassword(userEntity.getPassword());
        userDto.setRole(userEntity.getRole());
        return userDto;
    }

    public boolean checkPassword(String original, String hashed) {
        return BCrypt.verifyer().verify(original.getBytes(StandardCharsets.UTF_8), DatatypeConverter.parseHexBinary(hashed)).verified;
    }


    private String hashPassword(String password) {
        byte[] hash = BCrypt.withDefaults().hash(10,
                this.generateBcryptSalt(), password.getBytes(StandardCharsets.UTF_8));
        return DatatypeConverter.printHexBinary(hash);
    }

    private byte[] generateBcryptSalt() {
        try {
            SecureRandom secureRandom = SecureRandom.getInstance(SECURE_RANDOM_ALGORITHMS);
            return secureRandom.generateSeed(16);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("AlgorithmException unsupported: ", e);
        }
    }

}
