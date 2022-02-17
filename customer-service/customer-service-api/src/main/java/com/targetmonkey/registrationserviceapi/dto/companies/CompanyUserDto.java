package com.targetmonkey.registrationserviceapi.dto.companies;

import com.targetmonkey.registrationserviceapi.enums.Status;
import lombok.Data;

import javax.annotation.PostConstruct;
import java.util.Date;

@Data
public class CompanyUserDto {
    private long id;
    private String companyName;
    private String category;
    private String description;
    private long leadsCounts;
}
