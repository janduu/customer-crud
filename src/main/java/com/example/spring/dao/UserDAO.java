package com.example.spring.dao;

import com.example.hibernate.entity.User;

public interface UserDAO {
    void saveUser(User user);
    User getUserByUsername(String username);
}
