package com.springcourse.springmvcliquan.controllers;

import com.springcourse.springmvcliquan.domain.Customer;
import com.springcourse.springmvcliquan.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class CustomerController {
    private CustomerService customerService;

    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    @RequestMapping("/customers")
    public String listCustomers(Model model) {
        List<Customer> customers = customerService.listCustomers();
        model.addAttribute("customers", customers);
        return "customers";
    }

    @RequestMapping("/customer/{id}")
    public String getCustomer(@PathVariable Integer id, Model model) {
        model.addAttribute("customer", customerService.getCutomerById(id));
        return "customer";
    }

    @RequestMapping("/customer/delete/{id}")
    public String deleteCustomer(@PathVariable Integer id) {
        customerService.deleteCustomer(id);
        return "redirect:/customers";
    }

    @RequestMapping("/customer/new")
    public String newCustomer(Model model) {
        model.addAttribute("cutomer", new Customer());
        return "customerform";
    }

    @RequestMapping(value = "/customer", method = RequestMethod.POST)
    public String saveOrUpdateCustomer(Customer customer) {
        customerService.saveOrUpdateCustomer(customer);
        return "redirect:/customer/" + customer.getId();
    }
}
