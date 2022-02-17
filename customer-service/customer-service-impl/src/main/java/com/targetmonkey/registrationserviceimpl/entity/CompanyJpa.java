package com.targetmonkey.registrationserviceimpl.entity;


import com.targetmonkey.registrationserviceapi.enums.Status;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.mapstruct.EnumMapping;

import javax.persistence.*;
import javax.print.DocFlavor;
import java.util.Date;

@Data
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "companies")
public class CompanyJpa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "owner_id")
    private long ownerId;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "category")
    private String category;

    @Column(name = "description")
    private String description;

    @Column(name = "created")
    private Date created;

    @Column(name = "updated")
    private Date updated;

    @Column(name = "leads")
    private long leadsCounts;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

}