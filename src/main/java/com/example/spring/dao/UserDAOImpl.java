package com.example.spring.dao;

import com.example.hibernate.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import java.util.logging.Logger;

@Repository
public class UserDAOImpl implements UserDAO {

    private SessionFactory sessionFactory;
    private static final Logger LOGGER = Logger.getLogger(UserDAOImpl.class.getName());

    @Override
    public void saveUser(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public User getUserByUsername(String username) {
        Session session = sessionFactory.getCurrentSession();
        Query<User> q = session.createQuery("FROM User WHERE username=:findName",
                User.class);
        q.setParameter("findName", username);
        User user = null;
        try {
            user = q.getSingleResult();
        } catch (NoResultException ex) {
            LOGGER.info(ex.getMessage());
        }
        return user;
    }

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
