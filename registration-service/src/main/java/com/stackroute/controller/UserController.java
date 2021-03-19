package com.stackroute.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.exception.UserAlreadyExists;
import com.stackroute.exception.UserNotFound;
import com.stackroute.model.PreviousRead;
import com.stackroute.model.User;
import com.stackroute.service.RabbitMqSender;
import com.stackroute.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("api/v1")
@CrossOrigin(origins = "*")
public class UserController {
    private UserService userService;

    private RabbitMqSender rabbitMqSender;


    @Autowired
    public UserController(UserService userService, RabbitMqSender rabbitMqSender) {
        this.userService = userService;
        this.rabbitMqSender = rabbitMqSender;
    }

    @Value("${app.message}")
    private String message;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() throws UserNotFound {
        return new ResponseEntity<List<User>>((List<User>) userService.getAllUsers(), HttpStatus.OK);
    }

    @PostMapping("/upload")
    public ResponseEntity<User> uplaodImage(@RequestParam(value = "myfile") MultipartFile myfile, @RequestParam("item") String item) throws IOException, UserAlreadyExists {
        User user = new ObjectMapper().readValue(item, User.class);
        user.setUserId(UUID.randomUUID().toString());
        user.setPic(myfile.getBytes());
        user.setName(myfile.getOriginalFilename());
        user.setType(myfile.getContentType());

        User savedUser = userService.saveUser(user);
        rabbitMqSender.send(savedUser);
        System.out.println("Image saved");
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);

    }

    @PutMapping("/uploadnotes/{userEmail}")
    public void uploadNotes(@PathVariable String userEmail,@RequestBody PreviousRead previousRead) throws IOException, UserAlreadyExists, UserNotFound {
        userService.updateNotes(userEmail, previousRead);
    }





}
