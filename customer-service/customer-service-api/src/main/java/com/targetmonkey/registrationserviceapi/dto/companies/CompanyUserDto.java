package com.targetmonkey.registrationserviceapi.dto.companies;

import lombok.Data;

@Data
public class CompanyUserDto {
    private long id;
    private String companyName;
    private String category;
    private String description;
    private long leadsCounts;
}
