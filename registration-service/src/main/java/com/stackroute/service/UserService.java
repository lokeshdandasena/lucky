package com.stackroute.service;

import com.stackroute.exception.UserAlreadyExists;
import com.stackroute.exception.UserNotFound;
import com.stackroute.model.PreviousRead;
import com.stackroute.model.User;

import java.util.List;

public interface UserService
{

    User saveUser(User user) throws UserAlreadyExists;
    List<User> getAllUsers() throws UserNotFound;
    User updateNotes(String email, PreviousRead previousRead) throws UserAlreadyExists;
    User getUserByEmail(String email);
    //User save(User img);
}
