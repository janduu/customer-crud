package com.example.spring.service;

import com.example.hibernate.entity.Role;


public interface RoleService {
    void saveRole(Role role);
    Role getRoleByName(String name);
}
