package com.stackroute.controller;

import com.stackroute.config.JWTGenerator;
import com.stackroute.exception.InvalidCredentials;
import com.stackroute.model.User;
import com.stackroute.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// All Endpoints for authentication-service
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/v1/login")
public class UserController {

    private UserService userService;
    private JWTGenerator jwtGenerator;

    @Autowired
    public UserController(UserService userService, JWTGenerator jwtGenerator) {
        this.userService = userService;
        this.jwtGenerator = jwtGenerator;
    }

    // if user exists - token is provided or else exception occurs
    @PostMapping("/user")
    public ResponseEntity<?> getCredentials(@RequestBody User user) throws InvalidCredentials {
        ResponseEntity<?> responseEntity;
        User fetchedUser = userService.findByEmailAndPassword(user.getEmail(), user.getPassword());
        if (fetchedUser != null) {
            return new ResponseEntity<>(jwtGenerator.generateJwtToken(user), HttpStatus.OK);
        }
        throw new InvalidCredentials();
    }
}