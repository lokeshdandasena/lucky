package com.stackroute.service;

import com.stackroute.model.User;
import com.stackroute.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// main service to handle login
@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // queries the DB to find whether the user with credential exists.
    public User findByEmailAndPassword(String email, String password) {
        if (userRepository.findByEmailAndPassword(email, password) != null) {
            return userRepository.findByEmailAndPassword(email, password);
        }
        return null;
    }
}