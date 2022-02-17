package com.targetmonkey.registrationserviceimpl.repository;

import com.targetmonkey.registrationserviceimpl.domain.RoleJpa;
import com.targetmonkey.securitycommon.security.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleJpa, Long> {
}
