package com.targetmonkey.registrationserviceimpl.repository.interfaces;

import com.targetmonkey.registrationserviceimpl.entity.CompanyJpa;
import com.targetmonkey.registrationserviceimpl.entity.CustomerJpa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<CustomerJpa, Long> {
}
