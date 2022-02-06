package com.springboot.security.custom.basic.auth.model;

import com.springboot.security.custom.basic.auth.entity.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {

    private String email;
    private String password;
    private Role role;

}
