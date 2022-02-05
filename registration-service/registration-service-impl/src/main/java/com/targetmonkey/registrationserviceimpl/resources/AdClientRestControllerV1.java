package com.targetmonkey.registrationserviceimpl.resources;

import com.targetmonkey.registrationserviceapi.resource.v1.AdCleintRegistrationV1;
import com.targetmonkey.registrationserviceimpl.service.AdCustomerRegistrationService;
import com.targetmonkey.registrationserviceapi.dto.AdCustomerRegistrationDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class AdClientRestControllerV1 implements AdCleintRegistrationV1 {
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
