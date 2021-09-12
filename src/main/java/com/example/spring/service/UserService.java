package com.example.spring.service;

import com.example.hibernate.entity.User;
import com.example.spring.controller.UserRegistration;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    void saveUser(User user);
    User userOf(UserRegistration userRegistration);
    User getUserByUsername(String username);
}
