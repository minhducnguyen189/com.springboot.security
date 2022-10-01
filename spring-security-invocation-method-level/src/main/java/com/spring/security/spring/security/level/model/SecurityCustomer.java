package com.spring.security.spring.security.level.model;

import com.spring.security.spring.security.level.entity.CustomerEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SecurityCustomer implements UserDetails {

    private final CustomerEntity customerEntity;

    public SecurityCustomer(CustomerEntity customerEntity) {
        this.customerEntity = customerEntity;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(this.customerEntity.getRole()));
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.customerEntity.getPassword();
    }

    @Override
    public String getUsername() {
        return this.customerEntity.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
