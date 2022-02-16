package com.targetmonkey.registrationserviceimpl.facade;

import com.targetmonkey.registrationserviceapi.dto.customers.CustomerAdminDto;
import com.targetmonkey.registrationserviceapi.dto.customers.CustomerDto;
import com.targetmonkey.registrationserviceimpl.mappers.CustomersMapper;
import com.targetmonkey.registrationserviceimpl.service.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerFacade {

    @Autowired
    private CustomerServiceImpl customerService;

    //USER

    public CustomerDto getCustomerDtoFromUser(long id){
        return CustomersMapper.INSTANCE.toCustomerDto(customerService.getToId(id));
    }

    public CustomerDto editCustomerDtoFromUser(CustomerDto customerDto){
        var updatedDto = customerService.editCustomer(customerDto.getId(), customerDto);
        return CustomersMapper.INSTANCE.toCustomerDto(updatedDto);
    }

    //ADMIN

    public CustomerAdminDto getCustomerDtoFromAdmin(long id){
        return customerService.getToId(id);
    }

    public List<CustomerAdminDto> getAllCustomersFromAdmin(){
        return customerService.getAllCustomers();
    }

    public CustomerAdminDto editCustomerAdminFromAdmin(CustomerAdminDto customerAdminDto){
        return customerService.editCustomer(customerAdminDto.getId(), customerAdminDto);
    }

    public void deleteCustomer(long id){
        customerService.deleteCustomer(id);
    }

}
