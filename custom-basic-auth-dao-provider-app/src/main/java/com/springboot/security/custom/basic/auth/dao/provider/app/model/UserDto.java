package com.springboot.security.custom.basic.auth.dao.provider.app.model;

import com.springboot.security.custom.basic.auth.dao.provider.app.entity.RoleEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class UserDto {

    private String email;
    private String password;
    private Set<RoleDto> roles;

}
