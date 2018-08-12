package com.springcourse.springmvcliquan.services;

import com.springcourse.springmvcliquan.domain.Customer;
import com.springcourse.springmvcliquan.domain.DomainObject;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CustomerServiceImpl extends AbstractMapService implements CustomerService {

    @Override
    public List<DomainObject> listAll() {
        return super.listAll();
    }

    @Override
    public Customer getById(Integer id) {
        return (Customer)super.getById(id);
    }

    @Override
    public Customer saveOrUpdate(Customer domainObject) {
        return (Customer)super.saveOrUpdate(domainObject);
    }

    @Override
    public void delete(Integer id) {
        super.delete(id);
    }

    @Override
    protected void loadDomainObjects() {
        domainMap = new HashMap<>();

        Customer customer1 = new Customer();
        customer1.setId(1);
        customer1.setFirstName("David");
        customer1.setLastName("Lee");
        customer1.setAddress1("chang ping");
        customer1.setAddress2("baijia cheng");
        customer1.setCity("beijing");
        customer1.setEmail("david@qq.com");
        customer1.setPhoneNumber("13810960310");
        customer1.setState("beijng");
        customer1.setZipCode("102208");
        domainMap.put(1, customer1);

        Customer customer2 = new Customer();
        customer2.setId(2);
        customer2.setFirstName("jack");
        customer2.setLastName("tom");
        customer2.setAddress1("nan qu");
        customer2.setAddress2("shui zhen");
        customer2.setCity("qing dao");
        customer2.setEmail("jack@qq.com");
        customer2.setPhoneNumber("13267842353");
        customer2.setState("shan dong");
        customer2.setZipCode("745463");
        domainMap.put(2, customer2);

        Customer customer3 = new Customer();
        customer3.setId(3);
        customer3.setFirstName("jill");
        customer3.setLastName("will");
        customer3.setAddress1("beidaihe");
        customer3.setAddress2("luoma");
        customer3.setCity("qing huang dao");
        customer3.setEmail("jill@qq.com");
        customer3.setPhoneNumber("13762539456");
        customer3.setState("he bei");
        customer3.setZipCode("203852");
        domainMap.put(3, customer3);

    }
}
