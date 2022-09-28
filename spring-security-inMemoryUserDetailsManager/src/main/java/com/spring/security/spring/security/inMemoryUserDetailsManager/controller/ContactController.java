package com.spring.security.spring.security.inMemoryUserDetailsManager.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ContactController {


    @RequestMapping(method = RequestMethod.GET, path = "/v1/contact")
    public ResponseEntity<String> getContactDetail() {
        return ResponseEntity.ok("This is the contact details");
    }

}
