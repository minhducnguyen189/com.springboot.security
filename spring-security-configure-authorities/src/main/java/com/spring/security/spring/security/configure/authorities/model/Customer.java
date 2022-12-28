package com.spring.security.spring.security.configure.authorities.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Customer {

    private Integer id;
    private String email;
    private String role;

}