package com.spring.security.spring.security.jwt.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NoticeController {

    @RequestMapping(method = RequestMethod.GET, path = "/v1/notice")
    public ResponseEntity<String> getNoticeController() {
        return ResponseEntity.ok("This is the notice details");
    }

}
