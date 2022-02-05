package com.targetmonkey.registrationserviceimpl.service;

import com.targetmonkey.registrationserviceimpl.builders.AdCompanyBuilder;
import com.targetmonkey.registrationserviceimpl.repository.interfaces.AdCompanyRegRepository;
import com.targetmonkey.registrationserviceapi.dto.AdCompanyRegistrationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.targetmonkey.registrationserviceimpl.serviceapi.ApiRegistrationCompany;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdCompanyService implements ApiRegistrationCompany {
    @Autowired
    AdCompanyRegRepository adCompanyRegRepository;
    @Autowired
    AdCompanyBuilder adCompanyBuilder;


    @Override
    public void saveCompany(AdCompanyRegistrationDTO adCompanyRegistrationDTO) {
        adCompanyRegRepository.save(adCompanyBuilder.dtoToJpa(adCompanyRegistrationDTO));
    }

    @Override
    public List<AdCompanyRegistrationDTO> getAllCompany() {
        List<AdCompanyRegistrationDTO> adCompanyRegistrationDTOS = new ArrayList<>();
        adCompanyRegRepository
                .findAll().stream()
                .forEach(adCustomerRegistrationJpa ->
                        adCompanyRegistrationDTOS.add(adCompanyBuilder.jpaToDto(adCustomerRegistrationJpa)));
        return adCompanyRegistrationDTOS;
    }

    @Override
    public AdCompanyRegistrationDTO getToId(long id) {
        return adCompanyBuilder.jpaToDto(adCompanyRegRepository.getById(id));
    }

    @Override
    public void editCompany(long id, AdCompanyRegistrationDTO adCompanyRegistrationDTO) {
        var oldCompany = adCompanyBuilder.jpaToDto(adCompanyRegRepository.getById(id));

        oldCompany.setId(adCompanyRegistrationDTO.getId())
                        .setCompanyName(adCompanyRegistrationDTO.getCompanyName())
                        .setOwnerId(adCompanyRegistrationDTO.getOwnerId())
                        .setCategory(adCompanyRegistrationDTO.getCategory());

        adCompanyRegRepository.save(adCompanyBuilder.dtoToJpa(oldCompany));
    }

    @Override
    public void deleteCompany(long id) {
        adCompanyRegRepository.deleteById(id);
    }
}
