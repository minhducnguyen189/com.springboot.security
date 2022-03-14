package com.springboot.security.custom.basic.spring.security.auth.dao.provider.app.entity;

import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Length;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "user_details")
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

    @ElementCollection(targetClass = RoleEntity.class)
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_detail_id", referencedColumnName = "id")
    private Set<RoleEntity> roleEntities;

    private LocalDateTime createdDate;

}
