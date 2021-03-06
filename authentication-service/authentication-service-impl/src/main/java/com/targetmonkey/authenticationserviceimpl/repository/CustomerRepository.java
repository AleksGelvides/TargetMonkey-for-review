package com.targetmonkey.authenticationserviceimpl.repository;

import com.targetmonkey.authenticationserviceimpl.domain.CustomerJpa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerJpa, Long> {
    CustomerJpa findByUsername(String username);
}
