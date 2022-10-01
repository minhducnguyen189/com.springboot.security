package com.spring.security.spring.security.configure.authorities.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "customers")
@Getter
@Setter
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String email;
    private String password;
    private String role;

    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER)
    private Set<AuthorityEntity> authorities;

}
