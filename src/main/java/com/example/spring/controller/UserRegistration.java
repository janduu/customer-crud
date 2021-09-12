package com.example.spring.controller;

import com.example.hibernate.validation.FieldMatch;

import javax.validation.constraints.Size;


@FieldMatch(first = "password", second = "passwordConfirm", message = "Passwords does not match!")
public class UserRegistration {
     private String username;

     @Size(min = 6, max = 50, message = "Password must be at least 6 length")
     private String password;

     private String passwordConfirm;

     public String getUsername() {
          return username;
     }

     public void setUsername(String username) {
          this.username = username;
     }

     public String getPassword() {
          return password;
     }

     public void setPassword(String password) {
          this.password = password;
     }

     public String getPasswordConfirm() {
          return passwordConfirm;
     }

     public void setPasswordConfirm(String passwordConfirm) {
          this.passwordConfirm = passwordConfirm;
     }
}
