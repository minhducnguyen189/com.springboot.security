package com.springboot.security.custom.basic.spring.security.config;

import com.springboot.security.custom.basic.spring.security.entity.AuthorityEntity;
import com.springboot.security.custom.basic.spring.security.entity.CustomerEntity;
import com.springboot.security.custom.basic.spring.security.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class CustomerAuthenticationProvider implements AuthenticationProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerAuthenticationProvider.class);

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        LOGGER.info("CustomerAuthenticationProvider is triggered");
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        List<CustomerEntity> customerEntities = this.customerRepository.findByEmail(username);
        if (customerEntities.size() > 0) {
            if (passwordEncoder.matches(password, customerEntities.get(0).getPassword())) {
//                List<GrantedAuthority> authorities = new ArrayList<>();
//                authorities.add(new SimpleGrantedAuthority(customerEntities.get(0).getRole()));
                return new UsernamePasswordAuthenticationToken(username, password,
                        this.getGrantedAuthorities(customerEntities.get(0).getAuthorities()));
            } else {
                throw new BadCredentialsException("Invalid Password");
            }
        }
        throw new BadCredentialsException("No user registered with this details");
    }

    private List<GrantedAuthority> getGrantedAuthorities(Set<AuthorityEntity> authorityEntities) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorityEntities.forEach(a -> authorities.add(new SimpleGrantedAuthority(a.getAuthority())));
        return authorities;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
