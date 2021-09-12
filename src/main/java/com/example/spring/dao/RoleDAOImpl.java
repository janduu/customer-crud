package com.example.spring.dao;

import com.example.hibernate.entity.Role;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class RoleDAOImpl implements RoleDAO {

    private SessionFactory factory;

    @Override
    public void saveRole(Role role) {
        factory.getCurrentSession().save(role);
    }

    @Override
    public Role getByName(String name) {
        Session session = factory.getCurrentSession();
        Query<Role> query = session.createQuery("FROM Role WHERE authority=:name", Role.class);
        query.setParameter("name", name);
        return query.getSingleResult();
    }

    @Autowired
    public void setFactory(SessionFactory factory) {
        this.factory = factory;
    }
}
