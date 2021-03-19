//package com.stackroute.service;
//
//import com.stackroute.model.User;
//import com.stackroute.repository.UserRepository;
//import org.junit.Test;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.boot.test.mock.mockito.MockBean;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//public class UserServiceTest {
//
//    @MockBean
//    UserRepository userRepository;
//    User user;
//
//    @InjectMocks
//    UserService userService;
//
//    @BeforeEach
//    public void setUp(){
//        user = new User("k@123.com","password");
//    }
//
//    @Test
//    public void findByEmailAndPasswordShouldReturnUser(){
//        User user1 = new User("k@123.com","password");
//        when(userRepository.findByEmailAndPassword("k@123.com","password")).thenReturn(user1);
//        User foundUser = userService.findByEmailAndPassword("k@123.com","password");
//        assertEquals(foundUser,user1);
//        verify(userRepository,times(1)).findByEmailAndPassword("k@123.com","password");
//    }
//}