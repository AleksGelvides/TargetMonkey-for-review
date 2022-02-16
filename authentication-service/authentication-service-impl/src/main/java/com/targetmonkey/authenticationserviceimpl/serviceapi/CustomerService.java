package com.targetmonkey.authenticationserviceimpl.serviceapi;

import com.targetmonkey.authenticationserviceimpl.exceptions.CustomerWasRegisteredException;
import dto.CustomerRegistrationDto;

public interface CustomerService {

    void registrationCustomer(CustomerRegistrationDto customerRegistrationDto) throws CustomerWasRegisteredException;

}
