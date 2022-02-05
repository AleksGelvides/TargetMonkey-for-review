package com.targetmonkey.registrationserviceimpl.resources;

import com.targetmonkey.registrationserviceapi.dto.AdCompanyRegistrationDTO;
import com.targetmonkey.registrationserviceapi.resource.v1.AdClientCompanyRegistrationV1;
import com.targetmonkey.registrationserviceimpl.service.AdCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AdCompanyRestControllerV1 implements AdClientCompanyRegistrationV1 {
    @Autowired
    AdCompanyService service;

    @Override
    public String saveAdCompany(AdCompanyRegistrationDTO adCompanyRegistrationDTO) {
        service.saveCompany(adCompanyRegistrationDTO);
        return "Компания была сохранена для пользователя " + adCompanyRegistrationDTO.getOwnerId();
    }

    @Override
    public AdCompanyRegistrationDTO getToId(long id) {
        return service.getToId(id);
    }

    @Override
    public List<AdCompanyRegistrationDTO> getAll() {
        return service.getAllCompany();
    }

    @Override
    public String editAdCompany(long id, AdCompanyRegistrationDTO adCompanyRegistrationDTO) {
        service.editCompany(id, adCompanyRegistrationDTO);
        return "Компания была успешно изменена";
    }

    @Override
    public String deleteAdCompany(long id) {
        service.deleteCompany(id);
        return "Компания была удалена";
    }
}
