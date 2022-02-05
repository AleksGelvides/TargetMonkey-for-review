package com.targetmonkey.registrationserviceimpl.serviceapi;

import com.targetmonkey.registrationserviceapi.dto.AdCustomerRegistrationDTO;

import java.util.List;

public interface ApiRegistrationAdCustomer {

    void saveAdCustomer(AdCustomerRegistrationDTO adCustomerRegistrationDTO);

    List<AdCustomerRegistrationDTO> getAllCustomers();

    AdCustomerRegistrationDTO getToId(Long id);

    void editCustomer(long id, AdCustomerRegistrationDTO adCustomerRegistrationDTO);

    void deleteCustomer(long id);

}
