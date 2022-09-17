package com.springboot.security.oauth.github.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ResourceController {

    @GetMapping("/")
    public ResponseEntity<String> getResource(OAuth2AuthenticationToken oAuth2AuthenticationToken) {
        System.out.printf(String.valueOf(oAuth2AuthenticationToken.getPrincipal()));
        return ResponseEntity.ok("Hello! this is the secret resources!");
    }

}
