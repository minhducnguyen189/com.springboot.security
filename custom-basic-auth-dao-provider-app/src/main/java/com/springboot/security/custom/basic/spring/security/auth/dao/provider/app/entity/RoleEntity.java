package com.springboot.security.custom.basic.spring.security.auth.dao.provider.app.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "user_roles")
public class RoleEntity {

    @Id
    @GeneratedValue
    @Type(type = "uuid-char")
    private UUID id;

    private String roleName;

}
