package com.example.spring.controller;

import com.example.hibernate.entity.Customer;
import com.example.spring.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {
    private CustomerService customerService;

    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/list")
    public String listCustomers(Model model) {
        List<Customer> customers = customerService.getCustomers();
        model.addAttribute("customers", customers);
        return "customer-list";
    }

    @GetMapping("/customerAddForm")
    public String addCustomer(Model model) {
        model.addAttribute("customer", new Customer());
        return "customer-form";
    }

    @GetMapping("/customerUpdateForm")
    public String updateCustomer(@RequestParam("customerId") Integer id, Model model) {
        Customer customer = customerService.getCustomer(id);
        model.addAttribute("customer", customer);
        return "customer-form";
    }

    @GetMapping("/customerDelete")
    public String deleteCustomer(@RequestParam("customerId") Integer id) {
        customerService.deleteCustomerById(id);
        return "redirect:/customer/list";
    }

    @PostMapping("/saveCustomer")
    public String saveCustomer(@ModelAttribute("customer") Customer customer) {
        customerService.saveOrUpdateCustomer(customer);
        return "redirect:/customer/list";
    }

    @GetMapping("/search")
    public String searchCustomer(@RequestParam("theSearchName") String search, Model model) {
        List<Customer> customers =
                customerService.searchCustomersWithFirstNameOrLastNameLike(search);
        model.addAttribute("customers", customers);
        return "customer-list";
    }
}
