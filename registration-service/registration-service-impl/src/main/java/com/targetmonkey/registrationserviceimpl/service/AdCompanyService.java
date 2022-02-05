package com.targetmonkey.registrationserviceimpl.service;

import com.targetmonkey.registrationserviceimpl.builders.AdCompanyBuilder;
import com.targetmonkey.registrationserviceimpl.repository.interfaces.AdCompanyRegRepository;
import com.targetmonkey.registrationserviceapi.dto.AdCompanyRegistrationDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.targetmonkey.registrationserviceimpl.serviceapi.ApiRegistrationCompany;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class AdCompanyService implements ApiRegistrationCompany {
    @Autowired
    AdCompanyRegRepository adCompanyRegRepository;
    @Autowired
    AdCompanyBuilder adCompanyBuilder;


    @Override
    public void saveCompany(AdCompanyRegistrationDTO adCompanyRegistrationDTO) {
        adCompanyRegRepository.save(adCompanyBuilder.dtoToJpa(adCompanyRegistrationDTO));
        log.info("Компания " + adCompanyRegistrationDTO + " была сохранена");
    }

    @Override
    public List<AdCompanyRegistrationDTO> getAllCompany() {
        List<AdCompanyRegistrationDTO> adCompanyRegistrationDTOS = new ArrayList<>();
        adCompanyRegRepository
                .findAll().stream()
                .forEach(adCustomerRegistrationJpa ->
                        adCompanyRegistrationDTOS.add(adCompanyBuilder.jpaToDto(adCustomerRegistrationJpa)));
        log.info("Показать все компании. Всего компаний: " + adCompanyRegistrationDTOS.size());
        return adCompanyRegistrationDTOS;
    }

    @Override
    public AdCompanyRegistrationDTO getToId(long id) {
        var company = adCompanyBuilder.jpaToDto(adCompanyRegRepository.getById(id));
        log.info("Вызов компании по ID: " + id + " :: " + company);
        return company;
    }

    @Override
    public void editCompany(long id, AdCompanyRegistrationDTO adCompanyRegistrationDTO) {
        var oldCompany = adCompanyBuilder.jpaToDto(adCompanyRegRepository.getById(id));
        log.info("Изменение компании: " + oldCompany);
        oldCompany.setCompanyName(adCompanyRegistrationDTO.getCompanyName())
                .setOwnerId(adCompanyRegistrationDTO.getOwnerId())
                .setCategory(adCompanyRegistrationDTO.getCategory());
        log.info("Компания обновлена: " + oldCompany);
        adCompanyRegRepository.save(adCompanyBuilder.dtoToJpa(oldCompany));
        log.info("Компания сохранена");
    }

    @Override
    public void deleteCompany(long id) {
        adCompanyRegRepository.deleteById(id);
        log.info("Компания ID: " + id + " была удалена");
    }
}
