package com.targetmonkey.authenticationserviceimpl.service;

import com.targetmonkey.authenticationserviceimpl.domain.CustomerJpa;
import com.targetmonkey.authenticationserviceimpl.domain.RoleJpa;
import com.targetmonkey.authenticationserviceimpl.exceptions.CustomerWasRegisteredException;
import com.targetmonkey.authenticationserviceimpl.repository.CustomerRepository;
import com.targetmonkey.authenticationserviceimpl.repository.RoleRepository;
import com.targetmonkey.securitycommon.security.domain.Role;
import com.targetmonkey.securitycommon.security.domain.Status;
import dto.CustomerRegistrationDto;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService, UserDetailsService {
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
        var customerRole = roleRepository.findByName(String.valueOf(Role.ROLE_CUSTOMER));
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
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {

            var customerJpa = customerRepository.findByUsername(username);
            List<SimpleGrantedAuthority> authorities = customerJpa.getRoles().stream()
                    .map(roleJpa -> new SimpleGrantedAuthority(roleJpa.getName()))
                    .toList();

            return new User(customerJpa.getUsername(), customerJpa.getPassword(), authorities);
        }catch (NullPointerException e){
            throw new UsernameNotFoundException("This user not found");
        }
    }
}
