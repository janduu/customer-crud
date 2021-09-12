package com.example.spring.service;

import com.example.hibernate.entity.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> getCustomers();

    void saveOrUpdateCustomer(Customer newCustomer);

    Customer getCustomer(Integer id);

    void deleteCustomerById(Integer id);

    List<Customer> searchCustomersWithFirstNameOrLastNameLike(String like);
}
