package com.targetmonkey.registrationserviceimpl.repository.interfaces;

import com.targetmonkey.registrationserviceimpl.entity.CustomerJpa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdCustomerRegRepository extends JpaRepository<CustomerJpa, Long> {
}
