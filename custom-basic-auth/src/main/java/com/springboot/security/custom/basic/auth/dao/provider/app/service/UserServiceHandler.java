package com.springboot.security.custom.basic.auth.dao.provider.app.service;

import com.springboot.security.custom.basic.auth.dao.provider.app.model.RoleDto;
import com.springboot.security.custom.basic.auth.dao.provider.app.model.UserDto;
import com.springboot.security.custom.basic.auth.dao.provider.app.entity.RoleEntity;
import com.springboot.security.custom.basic.auth.dao.provider.app.entity.UserEntity;
import com.springboot.security.custom.basic.auth.dao.provider.app.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__({@Autowired}))
public class UserServiceHandler {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public UUID createUser(UserDto user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(user.getEmail());
        userEntity.setPassword(this.hashPassword(user.getPassword()));
        userEntity.setCreatedDate(LocalDateTime.now());
        userEntity.setRoleEntities(this.toRoleEntities(user.getRoles()));
        return this.userRepository.save(userEntity).getId();
    }

    private Set<RoleEntity> toRoleEntities(Set<RoleDto> roleDtoSet) {
        return roleDtoSet.stream()
                .map(r -> this.toRoleEntity(r, new RoleEntity()))
                .collect(Collectors.toSet());
    }

    private RoleEntity toRoleEntity(RoleDto roleDto, RoleEntity roleEntity) {
        roleEntity.setRoleName(roleDto.getRoleName());
        return roleEntity;
    }

    private String hashPassword(String password) {
        return passwordEncoder.encode(password);
    }
}
