package com.targetmonkey.dataserviceimpl.jparepositories.entities;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "ad_customer_registration_jpa")
public class AdCompanyRegistrationJpa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;
    private long ownerId;
    private String companyName;
    private String category;
}