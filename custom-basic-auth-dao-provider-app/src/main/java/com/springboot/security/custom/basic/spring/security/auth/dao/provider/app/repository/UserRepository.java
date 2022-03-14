package com.springboot.security.custom.basic.spring.security.auth.dao.provider.app.repository;

import com.springboot.security.custom.basic.spring.security.auth.dao.provider.app.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    UserEntity findByUsername(String username);

}
