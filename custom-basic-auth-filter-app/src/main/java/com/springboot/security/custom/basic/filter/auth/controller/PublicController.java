package com.springboot.security.custom.basic.filter.auth.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PublicController {

    @RequestMapping(method = RequestMethod.GET, path = "/v1/pub/hello", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> getDateTime() {
        return new ResponseEntity<>("Say Hello", HttpStatus.OK);
    }

}
