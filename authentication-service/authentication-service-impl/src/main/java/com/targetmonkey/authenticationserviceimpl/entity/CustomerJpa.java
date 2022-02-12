package com.targetmonkey.authenticationserviceimpl.entity;

import dto.CustomerRegistrationDto;
import enums.Status;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "customers")
@NoArgsConstructor
public class CustomerJpa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @CreatedDate
    @Column(name = "created")
    private Date created;

    @LastModifiedDate
    @Column(name = "updated")
    private Date updated;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @ToString.Exclude
    @Enumerated(EnumType.STRING)
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "roles_customers",
    joinColumns = {@JoinColumn(name = "customer_id", referencedColumnName = "id")},
    inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    List<RoleJpa> roles;

    public CustomerJpa(CustomerRegistrationDto dto, Status status, List<RoleJpa> role){
        this.name = dto.getName();
        this.surname = dto.getSurname();
        this.username = dto.getUserName();
        this.email = dto.getEmail();
        this.password = dto.getPassword();
        this.created = new Date();
        this.updated = created;
        this.status = status;
        this.roles = role;
    }

}
