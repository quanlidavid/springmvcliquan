package com.springcourse.springmvcliquan.services;

import com.springcourse.springmvcliquan.domain.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> listCustomers();

    Customer getCutomerById(Integer id);

    Customer saveOrUpdateCustomer(Customer customer);

    void deleteCustomer(Integer id);
}
