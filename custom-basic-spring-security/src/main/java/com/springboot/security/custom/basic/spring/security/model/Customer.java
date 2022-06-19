package com.springboot.security.custom.basic.spring.security.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Customer {

    private Integer id;
    private String email;
    private String role;

}
