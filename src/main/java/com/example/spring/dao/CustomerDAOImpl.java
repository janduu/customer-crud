package com.example.spring.dao;

import com.example.hibernate.entity.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerDAOImpl implements CustomerDAO {
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Customer> getCustomers() {
        Session session = sessionFactory.getCurrentSession();
        Query<Customer> query
                = session.createQuery("FROM Customer ORDER BY firstName, lastName", Customer.class);
        return query.getResultList();
    }

    @Override
    public void saveOrUpdateCustomer(Customer newCustomer) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(newCustomer);
    }

    @Override
    public Customer getCustomer(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Customer.class, id);
    }

    @Override
    public void deleteCustomerById(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        String queryString = "DELETE FROM Customer WHERE id=:customerId";
        Query<?> query = session.createQuery(queryString);

        query.setParameter("customerId", id);
        query.executeUpdate();
    }

    @Override
    public List<Customer> searchCustomersWithFirstNameOrLastName(String like) {
        if (like.trim().isEmpty()) {
            return getCustomers();
        }

        Session session = sessionFactory.getCurrentSession();

        String queryString = "FROM Customer WHERE LOWER(firstName)  LIKE :fname OR LOWER(lastName) LIKE :lname";
        Query<Customer> query = session.createQuery(queryString, Customer.class);

        like = "%" + like.toLowerCase() + "%";
        query.setParameter("fname", like);
        query.setParameter("lname", like);
        return query.getResultList();
    }
}
