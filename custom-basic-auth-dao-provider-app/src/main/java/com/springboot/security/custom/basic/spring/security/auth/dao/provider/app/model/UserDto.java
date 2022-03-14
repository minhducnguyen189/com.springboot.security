package com.springboot.security.custom.basic.spring.security.auth.dao.provider.app.model;

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
