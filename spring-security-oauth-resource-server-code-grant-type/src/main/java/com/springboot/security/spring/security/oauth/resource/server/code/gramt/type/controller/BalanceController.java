package com.springboot.security.spring.security.oauth.resource.server.code.gramt.type.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BalanceController {

    @RequestMapping(method = RequestMethod.GET, path = "/v1/balance")
    public ResponseEntity<String> getBalance() {
        return ResponseEntity.ok("This is your balance");
    }

}
