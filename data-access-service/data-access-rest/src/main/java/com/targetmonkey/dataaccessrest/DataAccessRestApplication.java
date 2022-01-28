package com.targetmonkey.dataaccessrest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class DataAccessRestApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataAccessRestApplication.class, args);
    }

}
