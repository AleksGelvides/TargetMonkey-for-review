package com.targetmonkey.registrationserviceimpl.security.services;

import com.targetmonkey.registrationserviceimpl.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private CustomerRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var customerJpa = repository.findByUsername(username);
        List<SimpleGrantedAuthority> authorities = customerJpa.getRoles().stream()
                .map(roleJpa -> new SimpleGrantedAuthority(roleJpa.getName().toString()))
                .toList();
        return new User(customerJpa.getUsername(), customerJpa.getPassword(), authorities);
    }
}
