package com.targetmonkey.authenticationserviceimpl.repository;

import com.targetmonkey.authenticationserviceimpl.entity.CustomerJpa;
import com.targetmonkey.authenticationserviceimpl.entity.RoleJpa;
import enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleJpa, Long> {
    RoleJpa findByName(Role name);
}
