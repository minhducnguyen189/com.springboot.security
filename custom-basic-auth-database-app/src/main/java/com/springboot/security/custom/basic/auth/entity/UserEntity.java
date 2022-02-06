package com.springboot.security.custom.basic.auth.entity;

import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "users")
public class UserEntity {


    @Id
    @GeneratedValue
    @Type(type = "uuid-char")
    private UUID id;

    @Email
    private String username;

    @Column(nullable = false)
    @Length(min = 6)
    private String password;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    private LocalDateTime createdDate;

}
