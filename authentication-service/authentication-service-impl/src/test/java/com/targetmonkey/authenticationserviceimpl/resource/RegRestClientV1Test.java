package com.targetmonkey.authenticationserviceimpl.resource;

import com.targetmonkey.authenticationserviceimpl.domain.CustomerJpa;
import com.targetmonkey.authenticationserviceimpl.exceptions.CustomerWasRegisteredException;
import com.targetmonkey.authenticationserviceimpl.repository.CustomerRepository;
import com.targetmonkey.securitycommon.security.domain.Status;
import dto.CustomerAuthDto;
import dto.CustomerRegistrationDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Testcontainers
public class RegRestClientV1Test {

    @Autowired
    TestRestTemplate restTemplate;

    @Autowired
    CustomerRepository repository;

    private CustomerRegistrationDto regDto;

    @BeforeEach
    public void initRegDto() {
        this.regDto = new CustomerRegistrationDto()
                .setName("TestName")
                .setSurname("TestSurname")
                .setEmail("testusermail@mail.com")
                .setUserName("TestUserName")
                .setPassword("TestUserPassword");
    }

    @BeforeEach
    public void clearDb() {
        repository.deleteAll();
    }

    @Test
    public void testSafeCustomerMustReturnedStatusOK() {
        var responceDto = new CustomerAuthDto("TestUserName", "You password");
        ResponseEntity<?> response = restTemplate.postForEntity("/api/v1/registration/create-customer"
                , regDto, CustomerAuthDto.class);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody(), responceDto);
    }

    @Test
    public void testSafeCustomerMustReturnedHttpStatus500() {
        var jpa = new CustomerJpa(regDto, Status.ACTIVE, new ArrayList<>());
        repository.save(jpa);
            ResponseEntity<?> response = restTemplate.postForEntity("/api/v1/registration/create-customer",
                    regDto, String.class);
            assertEquals(response.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);
            assertEquals(response.getBody(), "This user was registered");
    }
}
