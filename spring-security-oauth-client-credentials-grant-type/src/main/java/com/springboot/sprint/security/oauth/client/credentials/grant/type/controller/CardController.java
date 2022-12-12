package com.springboot.sprint.security.oauth.client.credentials.grant.type.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CardController {

    @RequestMapping(method = RequestMethod.GET, path = "/v1/card")
    public ResponseEntity<String> getCardDetail() {
        return ResponseEntity.ok("This is your card details");
    }

}
