package com.targetmonkey.authenticationserviceimpl.service;

import com.targetmonkey.authenticationserviceimpl.entity.CustomerJpa;
import com.targetmonkey.authenticationserviceimpl.entity.RoleJpa;
import com.targetmonkey.authenticationserviceimpl.exceptions.CustomerNotFoundException;
import com.targetmonkey.authenticationserviceimpl.exceptions.CustomerWasRegisteredException;
import com.targetmonkey.authenticationserviceimpl.repository.CustomerRepository;
import com.targetmonkey.authenticationserviceimpl.repository.RoleRepository;
import com.targetmonkey.authenticationserviceimpl.serviceapi.CustomerService;
import dto.CustomerAllDto;
import dto.CustomerRegistrationDto;
import enums.Role;
import enums.Status;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository,
                               RoleRepository roleRepository,
                               PasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @SneakyThrows
    public void registrationCustomer(CustomerRegistrationDto customerRegistrationDto) {
        var customerRole = roleRepository.findByName(Role.ROLE_CUSTOMER);
        List<RoleJpa> roles = new ArrayList<>();
        roles.add(customerRole);
        var customerJpa = new CustomerJpa(customerRegistrationDto, Status.ACTIVE, roles);
        customerJpa.setPassword(passwordEncoder.encode(customerJpa.getPassword()));
        if (customerRepository.findByUsername(customerRegistrationDto.getUserName()) != null) {
            throw new CustomerWasRegisteredException("This user was registered");
        } else
            customerRepository.save(customerJpa);
        log.info("IN registrationCustomer - customer: {} was saved", customerJpa);
    }

    @Override
    public CustomerAllDto getCustomerByUserName(String userName) {
        CustomerJpa customerJpa = null;
        List<Role> roles = new ArrayList<>();
        try {
            customerJpa = customerRepository.findByUsername(userName);
            if(customerJpa == null)
                throw new CustomerNotFoundException("customer not registered or was edited");
            customerJpa.getRoles()
                    .stream()
                    .forEach(roleJpa -> roles.add(roleJpa.getName()));
        }catch (NullPointerException | CustomerNotFoundException e){
            log.error(e.getMessage());
        }

        return new CustomerAllDto()
                .setName(customerJpa.getName())
                .setSurname(customerJpa.getSurname())
                .setUsername(customerJpa.getUsername())
                .setEmail(customerJpa.getEmail())
                .setPassword(customerJpa.getPassword())
                .setCreated(customerJpa.getCreated())
                .setUpdated(customerJpa.getUpdated())
                .setStatus(customerJpa.getStatus())
                .setRoles(roles);
    }
}
