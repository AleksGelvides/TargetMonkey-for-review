package com.targetmonkey.companymoderationservice;

import com.targetmonkey.companymoderationservice.domain.Company;
import com.targetmonkey.companymoderationservice.feign.FeignCompanyClient;
import com.targetmonkey.companymoderationservice.service.CompanyApproveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableEurekaClient
@EnableFeignClients
@SpringBootApplication
public class CompanyModerationServiceApplication implements CommandLineRunner {
    @Autowired
    CompanyApproveService service;

    public static void main(String[] args) {
        SpringApplication.run(CompanyModerationServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
