package com.targetmonkey.registrationserviceimpl.service;

import com.targetmonkey.registrationserviceimpl.repository.interfaces.AdCustomerRegRepository;
import com.targetmonkey.registrationserviceapi.dto.AdCustomerRegistrationDTO;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.targetmonkey.registrationserviceimpl.serviceapi.ApiRegistrationAdCustomer;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class AdCustomerRegistrationService implements ApiRegistrationAdCustomer {

    @Autowired
    AdCustomerRegRepository adCustomerRegRepository;


    @Override
    public void saveAdCustomer(AdCustomerRegistrationDTO adCustomerRegistrationDTO) {
//        adCustomerRegRepository.save(adCustomerBuilder.
//                dtoToJpa(adCustomerRegistrationDTO));
//        log.info("Пользователь сохранен");
    }

    @Override
    public List<AdCustomerRegistrationDTO> getAllCustomers() {
//        List<AdCustomerRegistrationDTO> adCustomerRegistrationDTOS = new ArrayList<>();
//                adCustomerRegRepository
//                .findAll().stream()
//                .forEach(jpa ->
//                        adCustomerRegistrationDTOS.add(adCustomerBuilder.jpaToDto(jpa)));
//        log.info("Показать всех пользователей. Всего: " + adCustomerRegistrationDTOS.size());
        return null;
    }

    @Override
    public AdCustomerRegistrationDTO getToId(int id) {
//        var customer = adCustomerBuilder.jpaToDto(adCustomerRegRepository.getById(id));
//        log.info("Вызов пользователя по ID: " + id + " :: " + customer);
        return null;
    }

    @Override
    public void editCustomer(int id, AdCustomerRegistrationDTO adCustomerRegistrationDTO) {
//        var oldCustomer = adCustomerBuilder.jpaToDto(adCustomerRegRepository.getById(id));
//        log.info("Изменение пользователя: " + oldCustomer);
//        oldCustomer.setName(adCustomerRegistrationDTO.getName())
//                .setMail(adCustomerRegistrationDTO.getMail())
//                .setPhone(adCustomerRegistrationDTO.getPhone())
//                .setPass(adCustomerRegistrationDTO.getPass());
//        log.info("Пользователь обновлен: " + oldCustomer);
//        adCustomerRegRepository.save(adCustomerBuilder.dtoToJpa(oldCustomer));
//        log.info("Пользователь сохранен");
    }


    @Override
    public void deleteCustomer(int id) {
//        adCustomerRegRepository.deleteById(id);
//        log.info("Пользователь ID: " + id + " был удален");
    }
}
