package com.springboot.security.hash.app.controller;

import com.springboot.security.hash.app.model.DataRequest;
import com.springboot.security.hash.app.model.MatchDataRequest;
import com.springboot.security.hash.app.service.BcryptService;
import com.springboot.security.hash.app.service.Pbkdf2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BcryptController {

    @Autowired
    private BcryptService bcryptService;

    @RequestMapping(method = RequestMethod.POST, path = "v1/cipher/hash/bcrypt", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> hashSHA256(@RequestBody DataRequest inputData) {
        return new ResponseEntity<>(this.bcryptService.hashBcrypt(inputData.getData()), HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.POST, path = "v1/cipher/hash/bcrypt/check", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> checkMatchSha256(@RequestBody MatchDataRequest inputData) {
        return new ResponseEntity<>(this.bcryptService.isBcryptMatch(inputData.getRawData(), inputData.getHashedData()), HttpStatus.OK);
    }

}
