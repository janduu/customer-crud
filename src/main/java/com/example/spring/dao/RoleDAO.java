package com.example.spring.dao;

import com.example.hibernate.entity.Role;

public interface RoleDAO {
    void saveRole(Role role);
    Role getByName(String name);
}
