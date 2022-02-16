package com.targetmonkey.authenticationserviceimpl.serviceapi;

import com.targetmonkey.authenticationserviceimpl.exceptions.CustomerNotFoundException;
import com.targetmonkey.authenticationserviceimpl.exceptions.CustomerWasRegisteredException;
import dto.CustomerAllDto;
import dto.CustomerRegistrationDto;

public interface CustomerService {

    void registrationCustomer(CustomerRegistrationDto customerRegistrationDto) throws CustomerWasRegisteredException;

}
