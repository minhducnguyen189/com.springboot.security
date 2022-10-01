package com.spring.security.spring.security.filtering.method.level.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "contacts")
@Getter
@Setter
public class ContactEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String contactName;

    private String contactEmail;

    private String subject;

    private String message;

}
