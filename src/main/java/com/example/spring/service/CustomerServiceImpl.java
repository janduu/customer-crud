package com.example.spring.service;

import com.example.hibernate.entity.Customer;
import com.example.spring.dao.CustomerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService{
    private CustomerDAO customerDAO;

    @Autowired
    public void setCustomerDAO(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    @Override
    @Transactional
    public List<Customer> getCustomers() {
        return customerDAO.getCustomers();
    }

    @Override
    @Transactional
    public void saveOrUpdateCustomer(Customer newCustomer) {
        customerDAO.saveOrUpdateCustomer(newCustomer);
    }

    @Override
    @Transactional
    public Customer getCustomer(Integer id) {
        return customerDAO.getCustomer(id);
    }

    @Override
    @Transactional
    public void deleteCustomerById(Integer id) {
        customerDAO.deleteCustomerById(id);
    }

    @Override
    @Transactional
    public List<Customer> searchCustomersWithFirstNameOrLastNameLike(String like) {
        return customerDAO.searchCustomersWithFirstNameOrLastName(like);
    }
}
