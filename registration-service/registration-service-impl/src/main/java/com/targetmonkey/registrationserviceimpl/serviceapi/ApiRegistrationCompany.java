package com.targetmonkey.registrationserviceimpl.serviceapi;

import com.targetmonkey.registrationserviceapi.dto.AdCompanyRegistrationDTO;

import java.util.List;

public interface ApiRegistrationCompany {

    void saveCompany(AdCompanyRegistrationDTO adCompanyRegistrationDTO);

    List<AdCompanyRegistrationDTO> getAllCompany();

    AdCompanyRegistrationDTO getToId(long id);

    void editCompany(long id, AdCompanyRegistrationDTO adCompanyRegistrationDTO);

    void deleteCompany(long id);

}
