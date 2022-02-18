package com.targetmonkey.registrationserviceimpl.service;

import com.targetmonkey.registrationserviceapi.dto.customers.CustomerAdminDto;
import com.targetmonkey.registrationserviceapi.dto.customers.CustomerDto;
import com.targetmonkey.registrationserviceimpl.domain.CustomerJpa;
import com.targetmonkey.registrationserviceimpl.exceptions.ObjectRepeatingException;
import com.targetmonkey.registrationserviceimpl.mappers.CustomersMapper;
import com.targetmonkey.registrationserviceimpl.repository.CustomerRepository;

import lombok.SneakyThrows;
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
        try {
            return CustomersMapper.INSTANCE.toCustomerAdminDto(
                    customerRepository.findByUsername(username));
        }catch (Exception e){
            throw new NotFoundException("User not found");
        }
    }

    @Override
    @SneakyThrows
    public CustomerAdminDto editCustomer(String username, CustomerDto newCustomer) {
        var jpa = customerRepository.findByUsername(username);
        CustomersMapper.INSTANCE.updateCustomerJpa(newCustomer, jpa);
        jpa.setUpdated(new Date());
        validationEditingCustomers(jpa);
        var updatedCustomer = customerRepository.save(jpa);
        return CustomersMapper.INSTANCE.toCustomerAdminDto(updatedCustomer);
    }

    @Override
    @SneakyThrows
    public CustomerAdminDto editCustomer(long id, CustomerAdminDto newCustomer) {
        var jpa = customerRepository.findById(id).orElseThrow(()
                -> new NotFoundException("Customer with this id not found"));
        CustomersMapper.INSTANCE.updateCustomerJpa(newCustomer, jpa);
        jpa.setUpdated(new Date());
        validationEditingCustomers(jpa);
        var updatedCustomer = customerRepository.save(jpa);
        return CustomersMapper.INSTANCE.toCustomerAdminDto(updatedCustomer);
    }

    @Override
    public void deleteCustomer(long id) throws NotFoundException{
        try {
            customerRepository.deleteById(id);
            log.info("IN deleteCustomer FOR ADMIN: пользователь удален с id: {}", id);
        }catch (Exception e){
            throw new NotFoundException("Customer not found");
        }
    }

    private void validationEditingCustomers(CustomerJpa jpa) throws ObjectRepeatingException{
        if(customerRepository.findByEmail(jpa.getEmail()) != null)
            throw new ObjectRepeatingException("This email was already used");
    }
}
