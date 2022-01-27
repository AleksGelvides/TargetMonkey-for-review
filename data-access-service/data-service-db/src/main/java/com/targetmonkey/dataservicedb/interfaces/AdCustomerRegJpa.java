package com.targetmonkey.dataservicedb.interfaces;

import com.targetmonkey.dataservicedb.entities.AdCustomerRegistrationJpa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdCustomerRegJpa extends JpaRepository<AdCustomerRegistrationJpa, Long> {
}
