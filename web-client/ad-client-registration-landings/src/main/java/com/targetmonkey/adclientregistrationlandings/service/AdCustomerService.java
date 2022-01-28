package com.targetmonkey.adclientregistrationlandings.service;

import com.targetmonkey.adclientregistrationlandings.entityMvc.AdCustomerRegistrationMvc;
import com.targetmonkey.adclientregistrationlandings.feign.RegistrationFeignClient;
import entities.AdCustomerRegistrationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdCustomerService {
    @Autowired
    RegistrationFeignClient registrationFeignClient;

    public void saveAdCustomer(AdCustomerRegistrationMvc customer){
        registrationFeignClient.saveAdCustomer(new AdCustomerRegistrationDTO()
                .setName(customer.getName())
                .setMail(customer.getMail())
                .setPhone(customer.getPhone())
                .setPass(customer.getPass()));
    }
}
