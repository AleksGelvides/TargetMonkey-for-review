package com.targetmonkey.dataserviceimpl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class DataServiceImplApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataServiceImplApplication.class, args);
    }

}
