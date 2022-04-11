package com.springboot.security.custom.basic.spring.security.service;

import com.springboot.security.custom.basic.spring.security.entity.CustomerEntity;
import com.springboot.security.custom.basic.spring.security.model.SecurityCustomer;
import com.springboot.security.custom.basic.spring.security.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankUserDetailsService implements UserDetailsService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        List<CustomerEntity> customerEntities = this.customerRepository.findByEmail(username);

        if (customerEntities.isEmpty()) {
            throw new UsernameNotFoundException("There are no user with email: " + username);
        }
        return new SecurityCustomer(customerEntities.get(0));
    }
}
