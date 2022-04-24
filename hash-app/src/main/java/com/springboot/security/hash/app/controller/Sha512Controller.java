package com.springboot.security.hash.app.controller;

import com.springboot.security.hash.app.model.DataRequest;
import com.springboot.security.hash.app.model.MatchDataRequest;
import com.springboot.security.hash.app.service.HashSha512Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Sha512Controller {

    @Autowired
    private HashSha512Service hashSha512Service;


    @RequestMapping(method = RequestMethod.POST, path = "v1/cipher/hash/sha512", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> hashSHA512(@RequestBody DataRequest inputData) {
        return new ResponseEntity<>(hashSha512Service.hashSHA512(inputData.getData()), HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.POST, path = "v1/cipher/hash/sha512/check", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> checkMatchSha512(@RequestBody MatchDataRequest inputData) {
        return new ResponseEntity<>(hashSha512Service.isSHA512Match(inputData.getRawData(), inputData.getHashedData()), HttpStatus.OK);
    }

}
