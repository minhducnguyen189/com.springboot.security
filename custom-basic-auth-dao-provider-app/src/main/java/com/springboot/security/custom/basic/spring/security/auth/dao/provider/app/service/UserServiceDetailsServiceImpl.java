package com.springboot.security.custom.basic.spring.security.auth.dao.provider.app.service;

import com.springboot.security.custom.basic.spring.security.auth.dao.provider.app.entity.UserEntity;
import com.springboot.security.custom.basic.spring.security.auth.dao.provider.app.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor(onConstructor = @__({@Autowired}))
public class UserServiceDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = this.userRepository.findByUsername(username);
        if (Objects.isNull(userEntity)) {
            throw new UsernameNotFoundException("User not found!");
        }
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        userEntity.getRoleEntities().forEach(r -> authorities.add(new SimpleGrantedAuthority(r.getRoleName())));
        return new User(userEntity.getUsername(), userEntity.getPassword(), authorities);
    }

}
