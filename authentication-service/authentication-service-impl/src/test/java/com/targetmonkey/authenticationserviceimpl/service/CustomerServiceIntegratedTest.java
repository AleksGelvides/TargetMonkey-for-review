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

    @Test
    public void testSaveUserInDataBase(){
        log.info("Start: testSaveUserInDataBase");
        var customerRegistration = new CustomerRegistrationDto()
                .setName("Test")
                .setSurname("Test")
                .setEmail("Test")
                .setUserName("Test")
                .setPassword("Test");
        customerService.registrationCustomer(customerRegistration);
        var customerFromDb = customerRepository.findById(1L).orElse(null);
        assertNotNull(customerFromDb);
        assertEquals(customerFromDb.getName(), customerRegistration.getName());
        assertEquals(customerFromDb.getSurname(), customerRegistration.getSurname());
        assertEquals(customerFromDb.getEmail(), customerRegistration.getEmail());
        assertEquals(customerFromDb.getUsername(), customerRegistration.getUserName());
        assertEquals(customerFromDb.getStatus(), Status.ACTIVE);
        assertNotEquals(customerFromDb.getPassword(), customerRegistration.getPassword());
    }

    @Test()
    public void testReturnExceptionAtDoubleUser(){
        var customerRegistration = new CustomerRegistrationDto()
                .setName("Test")
                .setSurname("Test")
                .setEmail("Test")
                .setUserName("Test")
                .setPassword("Test");
        customerService.registrationCustomer(customerRegistration);
        assertThrows(CustomerWasRegisteredException.class, () -> {
            customerService.registrationCustomer(customerRegistration);
        });
    }


    @BeforeEach
    public void voidClearDb(){
        customerRepository.deleteAll();
        log.info("db was cleared");
    }

}
