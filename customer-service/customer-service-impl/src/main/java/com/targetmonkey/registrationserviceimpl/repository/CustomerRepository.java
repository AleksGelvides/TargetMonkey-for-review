package com.targetmonkey.registrationserviceimpl.repository;

import com.targetmonkey.registrationserviceimpl.domain.CustomerJpa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerJpa, Long> {

    CustomerJpa findByUsername(String username);

    void deleteByUsername(String username);

    CustomerJpa findByEmail(String email);
}
