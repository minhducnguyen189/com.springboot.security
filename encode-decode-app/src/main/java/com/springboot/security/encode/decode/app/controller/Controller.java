package com.springboot.security.encode.decode.app.controller;

import com.springboot.security.encode.decode.app.model.DataRequest;
import com.springboot.security.encode.decode.app.service.EncodeDecodeService;
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
    private EncodeDecodeService encodeDecodeService;

    @RequestMapping(method = RequestMethod.POST, path = "v1/cipher/encode/base64", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> encodeData(@RequestBody DataRequest inputData) {
        return new ResponseEntity<>(encodeDecodeService.encodeBase64(inputData.getData()), HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.POST, path = "v1/cipher/decode/base64", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> decodeData(@RequestBody DataRequest inputData) {
        return new ResponseEntity<>(encodeDecodeService.decodeBase64(inputData.getData()), HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.POST, path = "v1/cipher/encode/url", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> encodeUrl(@RequestBody DataRequest inputData) {
        return new ResponseEntity<>(encodeDecodeService.encodeUrl(inputData.getData()), HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.POST, path = "v1/cipher/decode/url", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> decodeUrl(@RequestBody DataRequest inputData) {
        return new ResponseEntity<>(encodeDecodeService.decodeUrl(inputData.getData()), HttpStatus.CREATED);
    }

}
