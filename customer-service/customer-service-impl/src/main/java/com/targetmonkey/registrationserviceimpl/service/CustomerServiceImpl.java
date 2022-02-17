package com.targetmonkey.registrationserviceimpl.service;

import com.targetmonkey.registrationserviceapi.dto.customers.CustomerAdminDto;
import com.targetmonkey.registrationserviceapi.dto.customers.CustomerDto;
import com.targetmonkey.registrationserviceimpl.mappers.CustomersMapper;
import com.targetmonkey.registrationserviceimpl.repository.CustomerRepository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.targetmonkey.registrationserviceimpl.serviceapi.CustomerServiceAPI;
import org.webjars.NotFoundException;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerServiceAPI {

    @Autowired
    CustomerRepository customerRepository;


    @Override
    public List<CustomerAdminDto> getAllCustomers() {
        List<CustomerAdminDto> customers = customerRepository.findAll().stream()
                .map(CustomersMapper.INSTANCE::toCustomerAdminDto).toList();
        return customers;
    }

    @Override
    public CustomerAdminDto getToId(long id) throws NotFoundException {
        return CustomersMapper.INSTANCE.toCustomerAdminDto(
                customerRepository.findById(id)
                        .orElseThrow(() -> new NotFoundException("Customer not found"))
        );
    }

    @Override
    public CustomerAdminDto getByUserName(String username) {
        var customerAdminDto = CustomersMapper.INSTANCE.toCustomerAdminDto(
                customerRepository.findByUsername(username));
        if(customerAdminDto == null)
            throw new NotFoundException("User not found");
        return customerAdminDto;
    }

    @Override
    public CustomerAdminDto editCustomer(String username, CustomerDto newCustomer) {
        var jpa = customerRepository.findByUsername(username);
        CustomersMapper.INSTANCE.updateCustomerJpa(newCustomer, jpa);
        jpa.setUpdated(new Date());
        var updatedCustomer = customerRepository.save(jpa);
        return CustomersMapper.INSTANCE.toCustomerAdminDto(updatedCustomer);
    }

    @Override
    public CustomerAdminDto editCustomer(String username, CustomerAdminDto newCustomer) {
        var jpa = customerRepository.findByUsername(username);
        CustomersMapper.INSTANCE.updateCustomerJpa(newCustomer, jpa);
        jpa.setUpdated(new Date());
        var updatedCustomer = customerRepository.save(jpa);
        return CustomersMapper.INSTANCE.toCustomerAdminDto(updatedCustomer);
    }

    @Override
    public void deleteCustomer(String username) throws NotFoundException{
        try {
            customerRepository.deleteByUsername(username);
            log.info("IN deleteCustomer FOR ADMIN: пользователь удален с id: {}", username);
        }catch (Exception e){
            throw new NotFoundException("Customer not found");
        }
    }
}
