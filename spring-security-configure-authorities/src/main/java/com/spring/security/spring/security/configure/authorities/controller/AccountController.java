package com.spring.security.spring.security.configure.authorities.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    @RequestMapping(method = RequestMethod.GET, path = "/v1/accounts/{username}")
    public ResponseEntity<String> getAccountDetails(@PathVariable("username") String username) {
        return ResponseEntity.ok("This is the account details " + username);
    }

}
