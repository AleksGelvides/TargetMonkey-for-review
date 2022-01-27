package com.targetmonkey.dataserviceimpl.builders;

import com.targetmonkey.dataservicedb.entities.AdCustomerRegistrationJpa;
import entitiesDto.AdCustomerRegistrationDTO;
import org.springframework.stereotype.Component;

@Component
public class AdCustomerBuilder {

    public AdCustomerRegistrationJpa dtoToJpa
            (AdCustomerRegistrationDTO adCustomerRegistrationDTO){
        return new AdCustomerRegistrationJpa()
                .setName(adCustomerRegistrationDTO.getName())
                .setMail(adCustomerRegistrationDTO.getMail())
                .setPhone(adCustomerRegistrationDTO.getPhone())
                .setPass(adCustomerRegistrationDTO.getPass());
    }

    public AdCustomerRegistrationDTO jpaToDto
            (AdCustomerRegistrationJpa adCustomerRegistrationJpa){
        return new AdCustomerRegistrationDTO()
                .setId(adCustomerRegistrationJpa.getId())
                .setName(adCustomerRegistrationJpa.getName())
                .setMail(adCustomerRegistrationJpa.getMail())
                .setPhone(adCustomerRegistrationJpa.getPhone())
                .setPass(adCustomerRegistrationJpa.getPass());
    }
}
