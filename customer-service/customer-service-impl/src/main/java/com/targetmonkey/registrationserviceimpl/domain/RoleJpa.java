package com.targetmonkey.registrationserviceimpl.domain;

import com.targetmonkey.registrationserviceapi.enums.Status;
import com.targetmonkey.securitycommon.security.domain.Role;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "roles")
public class RoleJpa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    @Enumerated(EnumType.STRING)
    private Role name;

    @CreatedDate
    @Column(name = "created")
    private Date created;

    @LastModifiedDate
    @Column(name = "updated")
    private Date updated;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    List<CustomerJpa> roles;
}
