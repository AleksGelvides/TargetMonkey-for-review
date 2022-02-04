package com.targetmonkey.dataserviceimpl.jparepositories.interfaces;

import com.targetmonkey.dataserviceimpl.jparepositories.entities.AdCustomerRegistrationJpa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdCustomerRegJpa extends JpaRepository<AdCustomerRegistrationJpa, Long> {
}
