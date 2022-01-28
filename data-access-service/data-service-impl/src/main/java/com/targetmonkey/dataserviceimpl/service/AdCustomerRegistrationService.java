package com.targetmonkey.dataserviceimpl.service;

import com.targetmonkey.dataservicedb.interfaces.AdCustomerRegJpa;
import com.targetmonkey.dataserviceimpl.builders.AdCustomerBuilder;
import entities.AdCustomerRegistrationDTO;
import interfaces.DataServiceApiRegistrationAdCustomer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@ComponentScan("com.targetmonkey.dataservicedb")
@Service
public class AdCustomerRegistrationService implements DataServiceApiRegistrationAdCustomer {
    @Autowired
    AdCustomerBuilder adCustomerBuilder;
    @Autowired
    AdCustomerRegJpa adCustomerRegJpa;

    @Override
    public void saveAdCustomer(AdCustomerRegistrationDTO adCustomerRegistrationDTO) {
        adCustomerRegJpa.save(adCustomerBuilder.
                dtoToJpa(adCustomerRegistrationDTO));
    }

    @Override
    public List<AdCustomerRegistrationDTO> getAllCustomers() {
        List<AdCustomerRegistrationDTO> adCustomerRegistrationDTOS = new ArrayList<>();
                adCustomerRegJpa
                .findAll().stream()
                .forEach(adCustomerRegistrationJpa ->
                        adCustomerRegistrationDTOS.add(adCustomerBuilder.jpaToDto(adCustomerRegistrationJpa)));
        return adCustomerRegistrationDTOS;
    }
}
