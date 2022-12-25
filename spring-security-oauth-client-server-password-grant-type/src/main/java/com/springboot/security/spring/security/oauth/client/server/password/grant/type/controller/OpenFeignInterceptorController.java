package com.springboot.security.spring.security.oauth.client.server.password.grant.type.controller;

import com.springboot.security.spring.security.oauth.client.server.password.grant.type.api.SpringOAuth2ResourceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OpenFeignInterceptorController {

    @Autowired
    private SpringOAuth2ResourceClient springOAuth2ResourceClient;

    @RequestMapping(method = RequestMethod.GET, path = "/v1/oauth2/auth/password-grant-type/interceptor/card")
    public ResponseEntity<String> getCardMessage() {
        return ResponseEntity.ok(this.springOAuth2ResourceClient.getCardDetail());
    }

}
