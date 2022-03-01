package com.targetmonkey.authenticationserviceimpl.service;

import com.targetmonkey.authenticationserviceimpl.exceptions.CustomerWasRegisteredException;
import com.targetmonkey.authenticationserviceimpl.repository.CustomerRepository;
import com.targetmonkey.securitycommon.security.domain.Status;
import dto.CustomerRegistrationDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
@ActiveProfiles(value = "test")
@Testcontainers
public class CustomerServiceIntegratedTest {
    @Autowired
    CustomerServiceImpl customerService;
    @Autowired
    CustomerRepository customerRepository;
    private CustomerRegistrationDto customer;


    @BeforeEach
    public void init(){
        this.customer = new CustomerRegistrationDto()
                .setName("Test")
                .setSurname("Test")
                .setEmail("Test")
                .setUserName("Test")
                .setPassword("Test");
    }

    @BeforeEach
    public void voidClearDb(){
        customerRepository.deleteAll();
        log.info("db was cleared");
    }

    @Test
    public void testSaveUserInDataBase(){
        log.info("Start: testSaveUserInDataBase");

        customerService.registrationCustomer(customer);
        var customerFromDb = customerRepository.findByUsername(customer.getName());
        assertNotNull(customerFromDb);
        assertEquals(customerFromDb.getName(), customer.getName());
        assertEquals(customerFromDb.getSurname(), customer.getSurname());
        assertEquals(customerFromDb.getEmail(), customer.getEmail());
        assertEquals(customerFromDb.getUsername(), customer.getUserName());
        assertEquals(customerFromDb.getStatus(), Status.ACTIVE);
        assertNotEquals(customerFromDb.getPassword(), customer.getPassword());
    }

    @Test()
    public void testReturnExceptionAtDoubleUser(){
        var newDoubleCustomer = customer;
        customerService.registrationCustomer(customer);
        assertThrows(CustomerWasRegisteredException.class, () -> {
            customerService.registrationCustomer(newDoubleCustomer);
        });
    }

    @Test
    public void testLoadUserByUserNameWasReturnedUser(){
        customerService.registrationCustomer(customer);
        var user = customerService.loadUserByUsername(customer.getName());
        System.out.println(user);
        assertNotNull(user);
    }

    @Test
    public void testLoadByUserNameWasReturnedUsernameNotFoundException(){
        assertThrows(UsernameNotFoundException.class, () -> {
            customerService.loadUserByUsername(customer.getName());
        });
    }

}
