package com.targetmonkey.registrationserviceimpl.builders;

import com.targetmonkey.registrationserviceimpl.entity.AdCustomerRegistrationJpa;
import com.targetmonkey.registrationserviceapi.dto.AdCustomerRegistrationDTO;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AdCustomerBuilder {

    public AdCustomerRegistrationJpa dtoToJpa
            (AdCustomerRegistrationDTO dto){
        var jpa = new AdCustomerRegistrationJpa()
                .setName(dto.getName())
                .setMail(dto.getMail())
                .setPhone(dto.getPhone())
                .setPass(dto.getPass());
        return jpa;
    }

    public AdCustomerRegistrationDTO jpaToDto
            (AdCustomerRegistrationJpa jpa){
        var dto = new AdCustomerRegistrationDTO()
                .setId(jpa.getId())
                .setName(jpa.getName())
                .setMail(jpa.getMail())
                .setPhone(jpa.getPhone())
                .setPass(jpa.getPass());
        return dto;
    }
}
