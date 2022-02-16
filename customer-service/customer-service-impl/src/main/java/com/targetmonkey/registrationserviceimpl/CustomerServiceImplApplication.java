package com.targetmonkey.registrationserviceimpl;

import com.targetmonkey.registrationserviceimpl.service.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class CustomerServiceImplApplication implements CommandLineRunner {

    @Autowired
    private CustomerServiceImpl res;

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceImplApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
    }
}
