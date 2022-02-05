package com.targetmonkey.registrationserviceimpl.builders;

import com.targetmonkey.registrationserviceimpl.entity.AdCustomerRegistrationJpa;
import com.targetmonkey.registrationserviceapi.dto.AdCustomerRegistrationDTO;
import org.springframework.stereotype.Component;

@Component
public class AdCustomerBuilder {

    public AdCustomerRegistrationJpa dtoToJpa
            (AdCustomerRegistrationDTO dto){
        return new AdCustomerRegistrationJpa()
                .setName(dto.getName())
                .setMail(dto.getMail())
                .setPhone(dto.getPhone())
                .setPass(dto.getPass());
    }

    public AdCustomerRegistrationDTO jpaToDto
            (AdCustomerRegistrationJpa jpa){
        return new AdCustomerRegistrationDTO()
                .setId(jpa.getId())
                .setName(jpa.getName())
                .setMail(jpa.getMail())
                .setPhone(jpa.getPhone())
                .setPass(jpa.getPass());
    }
}
