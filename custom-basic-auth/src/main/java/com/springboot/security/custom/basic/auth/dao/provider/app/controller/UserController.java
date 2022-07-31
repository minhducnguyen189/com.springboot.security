package com.springboot.security.custom.basic.auth.dao.provider.app.controller;

import com.springboot.security.custom.basic.auth.dao.provider.app.model.UserDto;
import com.springboot.security.custom.basic.auth.dao.provider.app.service.UserServiceHandler;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@AllArgsConstructor(onConstructor = @__({@Autowired}))
public class UserController {

    private final UserServiceHandler userService;

    @RequestMapping(method = RequestMethod.POST, path = "/v1/auth/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UUID> createUser(@RequestBody UserDto userDto) {
        return new ResponseEntity<>(this.userService.createUser(userDto), HttpStatus.CREATED);
    }

}
