package com.targetmonkey.registrationserviceimpl.repository.interfaces;

import com.targetmonkey.registrationserviceimpl.entity.AdCustomerRegistrationJpa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdCustomerRegRepository extends JpaRepository<AdCustomerRegistrationJpa, Long> {
}
