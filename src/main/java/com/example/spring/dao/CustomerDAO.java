package com.example.spring.dao;

import com.example.hibernate.entity.Customer;

import java.util.List;

public interface CustomerDAO {
    List<Customer> getCustomers();

    void saveOrUpdateCustomer(Customer newCustomer);

    Customer getCustomer(Integer id);

    void deleteCustomerById(Integer id);

    List<Customer> searchCustomersWithFirstNameOrLastName(String like);
}
