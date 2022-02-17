package com.targetmonkey.registrationserviceimpl.serviceapi;

import com.targetmonkey.registrationserviceapi.dto.customers.CustomerAdminDto;
import com.targetmonkey.registrationserviceapi.dto.customers.CustomerDto;
import org.webjars.NotFoundException;

import java.util.List;

public interface CustomerServiceAPI {

    List<CustomerAdminDto> getAllCustomers();

    CustomerAdminDto getToId(long id) throws NotFoundException;

    CustomerAdminDto getByUserName(String username);

    CustomerAdminDto editCustomer(String username, CustomerDto newCustomer) throws NotFoundException;

    CustomerAdminDto editCustomer(String username, CustomerAdminDto customerAdminDto) throws NotFoundException;

    void deleteCustomer(String username) throws NotFoundException;

}
