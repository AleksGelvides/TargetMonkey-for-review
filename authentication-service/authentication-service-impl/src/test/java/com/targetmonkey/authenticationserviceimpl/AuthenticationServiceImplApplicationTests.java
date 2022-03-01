package com.targetmonkey.authenticationserviceimpl;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest
@ActiveProfiles(value = "test")
@Testcontainers
class AuthenticationServiceImplApplicationTests {

    @Test
    void contextLoads() {
    }

}
