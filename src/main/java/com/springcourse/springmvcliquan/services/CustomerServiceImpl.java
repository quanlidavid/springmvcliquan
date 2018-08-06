package com.springcourse.springmvcliquan.services;

import com.springcourse.springmvcliquan.domain.Customer;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CustomerServiceImpl implements CustomerService {
    Map<Integer, Customer> customers;

    public CustomerServiceImpl() {
        loadCustomers();
    }

    @Override
    public List<Customer> listCustomers() {
        return new ArrayList<>(customers.values());
    }

    @Override
    public Customer getCutomerById(Integer id) {
        return customers.get(id);
    }

    @Override
    public Customer saveOrUpdateCustomer(Customer customer) {
        if (customer != null) {
            if (customer.getId() == null) {
                customer.setId(getNextKey());
            }
            customers.put(customer.getId(), customer);
            return customer;
        } else {
            throw new RuntimeException("customer cann't be null");
        }
    }

    private Integer getNextKey() {
        if (customers.isEmpty()) {
            return 1;
        } else {
            return Collections.max(customers.keySet()) + 1;
        }
    }

    @Override
    public void deleteCustomer(Integer id) {
        customers.remove(id);
    }

    private void loadCustomers() {
        customers = new HashMap<>();

        Customer customer1 = new Customer();
        customer1.setId(1);
        customer1.setFirstName("David");
        customer1.setLastName("Lee");
        customer1.setAddress1("chang ping");
        customer1.setAddress2("baijia cheng");
        customer1.setCity("beijing");
        customer1.setEmail("david@qq.com");
        customer1.setCode("010");
        customer1.setPhoneNumber("13810960310");
        customer1.setState("beijng");
        customer1.setZip("102208");
        customers.put(1, customer1);

        Customer customer2 = new Customer();
        customer2.setId(2);
        customer2.setFirstName("jack");
        customer2.setLastName("tom");
        customer2.setAddress1("nan qu");
        customer2.setAddress2("shui zhen");
        customer2.setCity("qing dao");
        customer2.setEmail("jack@qq.com");
        customer2.setCode("320");
        customer2.setPhoneNumber("13267842353");
        customer2.setState("shan dong");
        customer2.setZip("745463");
        customers.put(2, customer2);

        Customer customer3 = new Customer();
        customer3.setId(3);
        customer3.setFirstName("jill");
        customer3.setLastName("will");
        customer3.setAddress1("beidaihe");
        customer3.setAddress2("luoma");
        customer3.setCity("qing huang dao");
        customer3.setEmail("jill@qq.com");
        customer3.setCode("020");
        customer3.setPhoneNumber("13762539456");
        customer3.setState("he bei");
        customer3.setZip("203852");
        customers.put(3, customer3);

    }
}
