package com.targetmonkey.authenticationserviceimpl.resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.targetmonkey.authenticationserviceimpl.repository.CustomerRepository;
import com.targetmonkey.authenticationserviceimpl.service.CustomerServiceImpl;
import dto.CustomerAuthDto;
import dto.CustomerRegistrationDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Testcontainers
public class AuthRestClientTest {

    @Autowired
    TestRestTemplate restTemplate;

    @Autowired
    CustomerServiceImpl service;

    @Autowired
    CustomerRepository repository;

    private CustomerRegistrationDto regDto;

    @BeforeEach
    public void clearDbAndCreateUser(){
        repository.deleteAll();
        this.regDto = new CustomerRegistrationDto()
                .setName("TestUserName")
                .setSurname("TestUserSurname")
                .setEmail("testuseremail@mail.com")
                .setUserName("TestUsername")
                .setPassword("TestUserPassword");
    }

    @Test
    public void testLoginMustReturnedJWTTokenAndUsername() throws JsonProcessingException {
        service.registrationCustomer(regDto);
        var dto = new CustomerAuthDto(regDto.getUserName(), regDto.getPassword());
        String json = restTemplate.postForObject("/api/v1/auth/login", dto, String.class);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(json);
        var jwt = node.get("jwt").asText();
        log.info("jwt: " + jwt);
        var username = node.get("username").asText();
        log.info("username: " + username);
        assertNotNull(jwt);
        assertEquals(username, regDto.getUserName());
    }

}
