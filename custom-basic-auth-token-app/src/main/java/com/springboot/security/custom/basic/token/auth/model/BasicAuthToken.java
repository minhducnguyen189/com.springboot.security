package com.springboot.security.custom.basic.token.auth.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BasicAuthToken {

    private String token;
    private Role role;

}
