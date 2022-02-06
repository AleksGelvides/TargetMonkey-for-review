package com.targetmonkey.registrationserviceimpl.serviceapi;

import com.targetmonkey.registrationserviceapi.dto.AdCompanyRegistrationDTO;

import java.util.List;

public interface ApiRegistrationCompany {

    void saveCompany(AdCompanyRegistrationDTO adCompanyRegistrationDTO);

    List<AdCompanyRegistrationDTO> getAllCompany();

    AdCompanyRegistrationDTO getToId(int id);

    void editCompany(int id, AdCompanyRegistrationDTO adCompanyRegistrationDTO);

    void deleteCompany(int id);

}
