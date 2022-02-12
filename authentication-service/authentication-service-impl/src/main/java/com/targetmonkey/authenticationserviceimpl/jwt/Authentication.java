package com.targetmonkey.authenticationserviceimpl.jwt;

import com.targetmonkey.authenticationserviceimpl.exceptions.AuthenticationError;
import com.targetmonkey.authenticationserviceimpl.exceptions.CustomerNotFoundException;
import com.targetmonkey.authenticationserviceimpl.service.CustomerServiceImpl;
import dto.CustomerAllDto;
import dto.CustomerAuthDto;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Authentication {
    private final CustomerServiceImpl customerService;
    private final BCryptPasswordEncoder encoder;

    @Autowired
    public Authentication(CustomerServiceImpl customerService,
                          BCryptPasswordEncoder encoder) {
        this.customerService = customerService;
        this.encoder = encoder;
    }

    @SneakyThrows
    public void authenticate(CustomerAuthDto dto) {
        CustomerAllDto customerAllDto = null;
        try {
            customerAllDto = customerService.getCustomerByUserName(dto.username());
        }catch (Exception e){
            log.error(e.getMessage());
        }
        if (customerAllDto == null)
            throw new CustomerNotFoundException("customer not found");
        if (!encoder.matches(dto.password(), customerAllDto.getPassword()))
            throw new AuthenticationError("Password not correctly");
    }
}
