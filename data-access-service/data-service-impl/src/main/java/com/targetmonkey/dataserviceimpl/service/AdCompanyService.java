package com.targetmonkey.dataserviceimpl.service;

import com.targetmonkey.dataserviceimpl.builders.AdCompanyBuilder;
import com.targetmonkey.dataserviceimpl.jparepositories.interfaces.AdCompanyRegJpa;
import dtoentities.AdCompanyRegistrationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import services.ApiRegistrationCompany;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdCompanyService implements ApiRegistrationCompany {
    @Autowired
    AdCompanyRegJpa adCompanyRegJpa;
    @Autowired
    AdCompanyBuilder adCompanyBuilder;


    @Override
    public void saveCompany(AdCompanyRegistrationDTO adCompanyRegistrationDTO) {
        adCompanyRegJpa.save(adCompanyBuilder.dtoToJpa(adCompanyRegistrationDTO));
    }

    @Override
    public List<AdCompanyRegistrationDTO> getAllCompany() {
        List<AdCompanyRegistrationDTO> adCompanyRegistrationDTOS = new ArrayList<>();
        adCompanyRegJpa
                .findAll().stream()
                .forEach(adCustomerRegistrationJpa ->
                        adCompanyRegistrationDTOS.add(adCompanyBuilder.jpaToDto(adCustomerRegistrationJpa)));
        return adCompanyRegistrationDTOS;
    }

    @Override
    public AdCompanyRegistrationDTO getToId(long id) {
        return adCompanyBuilder.jpaToDto(adCompanyRegJpa.getById(id));
    }

    @Override
    public void editCompany(long id, AdCompanyRegistrationDTO adCompanyRegistrationDTO) {
        var oldCompany = adCompanyBuilder.jpaToDto(adCompanyRegJpa.getById(id));

        oldCompany.setId(adCompanyRegistrationDTO.getId())
                        .setCompanyName(adCompanyRegistrationDTO.getCompanyName())
                        .setOwnerId(adCompanyRegistrationDTO.getOwnerId())
                        .setCategory(adCompanyRegistrationDTO.getCategory());

        adCompanyRegJpa.save(adCompanyBuilder.dtoToJpa(oldCompany));
    }

    @Override
    public void deleteCompany(long id) {
        adCompanyRegJpa.deleteById(id);
    }
}
