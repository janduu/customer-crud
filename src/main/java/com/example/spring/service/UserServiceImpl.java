package com.example.spring.service;

import com.example.hibernate.entity.Role;
import com.example.hibernate.entity.User;
import com.example.spring.controller.UserRegistration;
import com.example.spring.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private UserDAO userDAO;
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDAO.saveUser(user);
    }

    @Override
    public User userOf(UserRegistration userRegistration) {
        User user = new User();
        user.setUsername(userRegistration.getUsername());
        user.setPassword(userRegistration.getPassword());
        return user;
    }


    @Override
    @Transactional
    public User getUserByUsername(String username) {
        return userDAO.getUserByUsername(username);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userDAO.getUserByUsername(s);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                mapRolesToAuthorities(user.getRoleList()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getAuthority())).collect(Collectors.toList());
    }

    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Autowired
    public void setPasswordEncoder(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
}
