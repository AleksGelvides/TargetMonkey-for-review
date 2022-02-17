package com.targetmonkey.registrationserviceimpl.service;

import com.targetmonkey.registrationserviceapi.dto.customers.CustomerAdminDto;
import com.targetmonkey.registrationserviceapi.dto.customers.CustomerDto;
import com.targetmonkey.registrationserviceimpl.mappers.CustomersMapper;
import com.targetmonkey.registrationserviceimpl.repository.interfaces.CustomerRepository;

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
    public CustomerAdminDto editCustomer(long id, CustomerDto customerDto) {
        var customerOld =  getToId(id);
        log.info("IN editCustomer FOR CUSTOMERS: Найден пользователь с id: {}, Пользователь: {}", id, customerOld);

        CustomersMapper.INSTANCE.updateCustomerDTO(customerDto, customerOld);

        var updatedCustomer = customerRepository.save(
                CustomersMapper.INSTANCE.toCustomerJpa(customerOld.setUpdated(new Date())));
        log.info("IN editCustomer FOR CUSTOMERS: пользователь сохранен с id: {}, Пользователь: {}", id, customerOld);

        return CustomersMapper.INSTANCE.toCustomerAdminDto(updatedCustomer);
    }

    @Override
    public CustomerAdminDto editCustomer(long id, CustomerAdminDto customerAdminDto) {
        var customerOld =  getToId(id);
        log.info("IN editCustomer FOR CUSTOMERS: Найден пользователь с id: {}, Пользователь: {}", id, customerOld);

        CustomersMapper.INSTANCE.updateCustomerDTO(customerAdminDto, customerOld);

        var updatedCustomer = customerRepository.save(
                CustomersMapper.INSTANCE.toCustomerJpa(customerOld.setUpdated(new Date())));
        log.info("IN editCustomer FOR CUSTOMERS: пользователь сохранен с id: {}, Пользователь: {}", id, customerOld);

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
}
