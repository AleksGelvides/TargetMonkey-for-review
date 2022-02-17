package com.targetmonkey.authenticationserviceimpl.repository;

import com.targetmonkey.authenticationserviceimpl.domain.RoleJpa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleJpa, Long> {
    RoleJpa findByName(String name);
}
