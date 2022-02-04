package com.targetmonkey.dataserviceimpl.service;

import com.targetmonkey.dataserviceimpl.jparepositories.interfaces.AdCustomerRegJpa;
import com.targetmonkey.dataserviceimpl.builders.AdCustomerBuilder;
import dtos.AdCustomerRegistrationDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import services.ApiRegistrationAdCustomer;

import java.util.ArrayList;
import java.util.List;

@ComponentScan("com.targetmonkey.dataservicedb")
@Service
public class AdCustomerRegistrationService implements ApiRegistrationAdCustomer {
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
                .forEach(jpa ->
                        adCustomerRegistrationDTOS.add(adCustomerBuilder.jpaToDto(jpa)));
        return adCustomerRegistrationDTOS;
    }

    @Override
    public AdCustomerRegistrationDTO getToId(Long id) {
        return adCustomerBuilder.jpaToDto(adCustomerRegJpa.getById(id));
    }

    @Override
    public void editCustomer(long id, AdCustomerRegistrationDTO adCustomerRegistrationDTO) {
        var oldCustomer = adCustomerBuilder.jpaToDto(adCustomerRegJpa.getById(id));

        oldCustomer.setName(adCustomerRegistrationDTO.getName())
                .setMail(adCustomerRegistrationDTO.getMail())
                .setPhone(adCustomerRegistrationDTO.getPhone())
                .setPass(adCustomerRegistrationDTO.getPass());

        adCustomerRegJpa.save(adCustomerBuilder.dtoToJpa(oldCustomer));
    }

    @Override
    public void deleteCustomer(long id) {
        adCustomerRegJpa.deleteById(id);
    }
}
