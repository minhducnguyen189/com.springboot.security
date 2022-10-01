package com.spring.security.spring.security.invocation.method.level.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "authorities")
@Getter
@Setter
public class AuthorityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;

    private String authority;

}
