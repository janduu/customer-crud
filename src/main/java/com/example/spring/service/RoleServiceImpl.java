package com.example.spring.service;

import com.example.hibernate.entity.Role;
import com.example.spring.dao.RoleDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoleServiceImpl implements RoleService {
    private RoleDAO roleDAO;

    @Override
    @Transactional
    public void saveRole(Role role) {
        roleDAO.saveRole(role);
    }

    @Override
    @Transactional
    public Role getRoleByName(String name) {
        return roleDAO.getByName(name);
    }

    @Autowired
    public void setRoleDAO(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }
}
