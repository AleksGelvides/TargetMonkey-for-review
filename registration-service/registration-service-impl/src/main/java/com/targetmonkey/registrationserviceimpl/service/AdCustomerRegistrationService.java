package com.targetmonkey.registrationserviceimpl.service;

import com.targetmonkey.registrationserviceimpl.repository.interfaces.AdCustomerRegRepository;
import com.targetmonkey.registrationserviceimpl.builders.AdCustomerBuilder;
import com.targetmonkey.registrationserviceapi.dto.AdCustomerRegistrationDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.targetmonkey.registrationserviceimpl.serviceapi.ApiRegistrationAdCustomer;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdCustomerRegistrationService implements ApiRegistrationAdCustomer {
    @Autowired
    AdCustomerBuilder adCustomerBuilder;
    @Autowired
    AdCustomerRegRepository adCustomerRegRepository;


    @Override
    public void saveAdCustomer(AdCustomerRegistrationDTO adCustomerRegistrationDTO) {
        adCustomerRegRepository.save(adCustomerBuilder.
                dtoToJpa(adCustomerRegistrationDTO));
    }

    @Override
    public List<AdCustomerRegistrationDTO> getAllCustomers() {
        List<AdCustomerRegistrationDTO> adCustomerRegistrationDTOS = new ArrayList<>();
                adCustomerRegRepository
                .findAll().stream()
                .forEach(jpa ->
                        adCustomerRegistrationDTOS.add(adCustomerBuilder.jpaToDto(jpa)));
        return adCustomerRegistrationDTOS;
    }

    @Override
    public AdCustomerRegistrationDTO getToId(Long id) {
        return adCustomerBuilder.jpaToDto(adCustomerRegRepository.getById(id));
    }

    @Override
    public void editCustomer(long id, AdCustomerRegistrationDTO adCustomerRegistrationDTO) {
        var oldCustomer = adCustomerBuilder.jpaToDto(adCustomerRegRepository.getById(id));

        oldCustomer.setName(adCustomerRegistrationDTO.getName())
                .setMail(adCustomerRegistrationDTO.getMail())
                .setPhone(adCustomerRegistrationDTO.getPhone())
                .setPass(adCustomerRegistrationDTO.getPass());

        adCustomerRegRepository.save(adCustomerBuilder.dtoToJpa(oldCustomer));
    }

    @Override
    public void deleteCustomer(long id) {
        adCustomerRegRepository.deleteById(id);
    }
}
