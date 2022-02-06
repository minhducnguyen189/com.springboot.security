package com.springboot.security.custom.basic.token.auth.model;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class BasicAuthToken {

    private String token;
    private List<Role> roles;


}
