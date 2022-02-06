package com.springboot.security.custom.basic.filter.auth.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class PrivateController {

    @RequestMapping(method = RequestMethod.GET, path = "/v1/datetime", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> getDateTime() {
        return new ResponseEntity<>(LocalDateTime.now().toString(), HttpStatus.OK);
    }

}
