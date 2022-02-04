package com.targetmonkey.dataserviceimpl.rest;

import clients.AdCleintRegistration;
import com.targetmonkey.dataserviceimpl.service.AdCustomerRegistrationService;
import dtos.AdCustomerRegistrationDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
@Slf4j
public class AdClientRestController implements AdCleintRegistration {
    @Autowired
    private AdCustomerRegistrationService service;

    @Override
    public String saveAdCustomer(AdCustomerRegistrationDTO adCustomerRegistrationDTO) {
        return "Пользователь сохранен";
    }

    @Override
    public List<AdCustomerRegistrationDTO> getAll() {
        return service.getAllCustomers();
    }

    @Override
    public AdCustomerRegistrationDTO getToId(long id) {
        return service.getToId(id);
    }

    @Override
    public String editAdCustomer(long id, AdCustomerRegistrationDTO adCustomerRegistrationDTO) {
        service.editCustomer(id, adCustomerRegistrationDTO);
        return "Пользователь успешно отредактирован";
    }

    @Override
    public String deleteAdCustomer(long id) {
        service.deleteCustomer(id);
        return "Пользователь успешно удален";
    }
}
