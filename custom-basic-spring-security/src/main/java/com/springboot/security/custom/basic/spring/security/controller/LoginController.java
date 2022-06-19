package com.springboot.security.custom.basic.spring.security.controller;

import com.springboot.security.custom.basic.spring.security.model.Customer;
import com.springboot.security.custom.basic.spring.security.model.UserLogin;
import com.springboot.security.custom.basic.spring.security.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class LoginController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping(method = RequestMethod.POST, path = "/v1/user")
    public ResponseEntity<Customer> getUserDetailsAfterLogin(@RequestBody UserLogin userLogin) {
        Customer customer = this.customerService.getCustomerByEmail(userLogin.getEmail());
        return ResponseEntity.ok(customer);
    }

}
