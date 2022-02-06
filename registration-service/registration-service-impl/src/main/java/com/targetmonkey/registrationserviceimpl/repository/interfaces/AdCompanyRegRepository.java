package com.targetmonkey.registrationserviceimpl.repository.interfaces;

import com.targetmonkey.registrationserviceimpl.entity.AdCompanyRegistrationJpa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdCompanyRegRepository extends JpaRepository<AdCompanyRegistrationJpa, Long> {
}
