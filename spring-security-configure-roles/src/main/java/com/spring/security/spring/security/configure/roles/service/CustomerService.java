package com.spring.security.spring.security.configure.roles.service;


import com.spring.security.spring.security.configure.roles.model.Customer;
import com.spring.security.spring.security.configure.roles.repository.CustomerRepository;
import com.spring.security.spring.security.configure.roles.entity.CustomerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer getCustomerByEmail(String email) {
        List<CustomerEntity> customerEntities = this.customerRepository.findByEmail(email);
        if (!customerEntities.isEmpty()) {
            CustomerEntity customerEntity = customerEntities.get(0);
            Customer customer = new Customer();
            customer.setId(customerEntity.getId());
            customer.setEmail(customerEntity.getEmail());
            customer.setRole(customerEntity.getRole());
            return customer;
        }
        return new Customer();
    }

}
