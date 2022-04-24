package com.springboot.security.symmetric.encrypt.decrypt.app.controller;

import com.springboot.security.symmetric.encrypt.decrypt.app.model.DataRequest;
import com.springboot.security.symmetric.encrypt.decrypt.app.service.Aes256Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @Autowired
    private Aes256Service aes256Service;

    @RequestMapping(method = RequestMethod.GET, path = "v1/cipher/encrypt/key", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> createEncryptKey() {
        return new ResponseEntity<>(aes256Service.createEncryptKey(), HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.POST, path = "v1/cipher/encrypt", produces = MediaType.TEXT_PLAIN_VALUE)
    ResponseEntity<String> encryptData(@RequestBody DataRequest inputData) {
        return new ResponseEntity<>(aes256Service.encryptData(inputData.getData()), HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.POST, path = "v1/cipher/decrypt", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> decryptData(@RequestBody DataRequest inputData) {
        return new ResponseEntity<>(aes256Service.decryptData(inputData.getData()), HttpStatus.CREATED);
    }

}
