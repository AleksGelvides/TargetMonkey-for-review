package com.targetmonkey.registrationserviceimpl.repository;

import com.targetmonkey.registrationserviceimpl.domain.CompanyJpa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompanyRepository extends JpaRepository<CompanyJpa, Long> {

    List<CompanyJpa> findByOwnerId(long id);

    CompanyJpa findByOwnerIdAndId(long ownerId, long companyId);
}
