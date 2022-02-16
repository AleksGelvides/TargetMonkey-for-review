package com.targetmonkey.registrationserviceimpl;

import com.targetmonkey.registrationserviceapi.dto.customers.CustomerAdminDto;
import com.targetmonkey.registrationserviceapi.dto.customers.CustomerDto;
import com.targetmonkey.registrationserviceimpl.entity.CustomerJpa;
import com.targetmonkey.registrationserviceimpl.mappers.CustomersMapper;
import com.targetmonkey.registrationserviceimpl.repository.interfaces.AdCustomerRegRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class CustomerServiceImplApplication implements CommandLineRunner {

    @Autowired
    private AdCustomerRegRepository res;

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceImplApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        CustomerJpa customerJpa = res.findById(50L).orElse(null);
        System.out.println(customerJpa);
        CustomerDto customerDto = CustomersMapper.INSTANCE.toCustomerDto(customerJpa);
        System.out.println(customerDto);
        CustomerAdminDto customerAdminDto = CustomersMapper.INSTANCE.toCustomerAdminDto(customerJpa);
        System.out.println(customerAdminDto);
    }
}
