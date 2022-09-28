package com.spring.security.spring.security.custom.userDetailsService.service;

import com.spring.security.spring.security.custom.userDetailsService.entity.CustomerEntity;
import com.spring.security.spring.security.custom.userDetailsService.model.SecurityCustomer;
import com.spring.security.spring.security.custom.userDetailsService.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankUserDetailsService implements UserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BankUserDetailsService.class);

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LOGGER.info("BankUserDetailsService is triggered!");
        List<CustomerEntity> customerEntities = this.customerRepository.findByEmail(username);

        if (customerEntities.isEmpty()) {
            throw new UsernameNotFoundException("There are no user with email: " + username);
        }
        return new SecurityCustomer(customerEntities.get(0));
    }
}
