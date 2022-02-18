package com.targetmonkey.authenticationserviceimpl.resource;

import com.targetmonkey.authenticationserviceimpl.service.CustomerServiceImpl;
import dto.CustomerAuthDto;
import dto.CustomerRegistrationDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import resource.RegistrationRestClientV1;


@Slf4j
@RestController
@RequestMapping("/api/v1/registration/")
public class RegRestClientV1 implements RegistrationRestClientV1 {
    private final CustomerServiceImpl service;

    @Autowired
    public RegRestClientV1(CustomerServiceImpl service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<?> saveCustomer(CustomerRegistrationDto customerRegistrationDto) {
        try {
            service.registrationCustomer(customerRegistrationDto);
        }catch (Exception e){
            log.error(e.getMessage());
            e.printStackTrace();
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(new CustomerAuthDto(customerRegistrationDto.getUserName(), "You password"),
                HttpStatus.OK);
    }
}
