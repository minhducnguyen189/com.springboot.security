package com.springboot.security.custom.basic.spring.security.model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Contact {

    private Integer id;

    private String contactName;

    private String contactEmail;

    private String subject;

    private String message;

}
