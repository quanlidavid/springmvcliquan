package com.springcourse.springmvcliquan.controllers;

import com.springcourse.springmvcliquan.domain.Customer;
import com.springcourse.springmvcliquan.services.CustomerService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class CustomerControllerTest {
    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerController customerController;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }

    @Test
    public void testList() throws Exception {
        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer());
        customers.add(new Customer());

        when(customerService.listAll()).thenReturn((List)customers);
        mockMvc.perform(get("/customer/"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("customers", hasSize(2)))
                .andExpect(view().name("customer/list"));
    }

    @Test
    public void testShow() throws Exception {
        Integer id = 1;

        when(customerService.getById(id)).thenReturn(new Customer());
        mockMvc.perform(get("/customer/show/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("customer/show"))
                .andExpect(model().attribute("customer", instanceOf(Customer.class)));
    }

    @Test
    public void testEdit() throws Exception {
        Integer id = 1;

        when(customerService.getById(id)).thenReturn(new Customer());
        mockMvc.perform(get("/customer/edit/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("customer/customerform"))
                .andExpect(model().attribute("customer", instanceOf(Customer.class)));
    }

    @Test
    public void testNew() throws Exception {
        verifyZeroInteractions(customerService);
        mockMvc.perform(get("/customer/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("customer/customerform"))
                .andExpect(model().attribute("customer", instanceOf(Customer.class)));
    }

    @Test
    public void testDelete() throws Exception {
        Integer id = 1;
        mockMvc.perform(get("/customer/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/customer/list"));
        verify(customerService,times(1)).delete(id);
    }

    @Test
    public void testSaveOrUpdate() throws Exception {
//        Integer id=1;
//        String lastName = "Lee";
//        String firstName = "David";
//        String phoneNumber = "13746454296";

        Customer customer = new Customer();
//        customer.setId(id);
//        customer.setLastName(lastName);
//        customer.setFirstName(firstName);
//        customer.setPhoneNumber(phoneNumber);

        when(customerService.saveOrUpdate(any())).thenReturn(customer);
        mockMvc.perform(post("/customer")
                .param("id", "1")
                .param("lastName", "Lee")
                .param("firstName", "David"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/customer/show/1"))
                .andExpect(model().attribute("customer", instanceOf(Customer.class)))
                .andExpect(model().attribute("customer", hasProperty("id", is(1))))
                .andExpect(model().attribute("customer", hasProperty("lastName", is("Lee"))))
                .andExpect(model().attribute("customer", hasProperty("firstName", is("David"))));

        ArgumentCaptor<Customer> boundCustomer = ArgumentCaptor.forClass(Customer.class);
        verify(customerService).saveOrUpdate(boundCustomer.capture());
        assertEquals(boundCustomer.getValue().getId(), new Integer(1));
        assertEquals(boundCustomer.getValue().getFirstName(),"David");
        assertEquals(boundCustomer.getValue().getLastName(), "Lee");
    }
}
