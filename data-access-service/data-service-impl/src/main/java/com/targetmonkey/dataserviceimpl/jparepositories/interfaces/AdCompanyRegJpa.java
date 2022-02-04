package com.targetmonkey.dataserviceimpl.jparepositories.interfaces;

import com.targetmonkey.dataserviceimpl.jparepositories.entities.AdCompanyRegistrationJpa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdCompanyRegJpa extends JpaRepository<AdCompanyRegistrationJpa, Long> {
}
