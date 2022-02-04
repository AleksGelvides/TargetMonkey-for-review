package com.targetmonkey.dataserviceimpl.builders;

import com.targetmonkey.dataserviceimpl.jparepositories.entities.AdCompanyRegistrationJpa;
import dtoentities.AdCompanyRegistrationDTO;
import org.springframework.stereotype.Component;

@Component
public class AdCompanyBuilder {

    public AdCompanyRegistrationJpa dtoToJpa
            (AdCompanyRegistrationDTO adCompanyRegistrationDTO){
        return new AdCompanyRegistrationJpa()
                .setId(adCompanyRegistrationDTO.getId())
                .setCompanyName(adCompanyRegistrationDTO.getCompanyName())
                .setCategory(adCompanyRegistrationDTO.getCategory())
                .setOwnerId(adCompanyRegistrationDTO.getOwnerId());
    }

    public AdCompanyRegistrationDTO jpaToDto
            (AdCompanyRegistrationJpa adCompanyRegistrationJpa){
        return new AdCompanyRegistrationDTO()
                .setId(adCompanyRegistrationJpa.getId())
                .setCompanyName(adCompanyRegistrationJpa.getCompanyName())
                .setCategory(adCompanyRegistrationJpa.getCategory())
                .setOwnerId(adCompanyRegistrationJpa.getOwnerId());
    }
}
