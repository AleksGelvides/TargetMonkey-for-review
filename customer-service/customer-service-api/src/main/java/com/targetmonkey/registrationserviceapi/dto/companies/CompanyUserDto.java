package com.targetmonkey.registrationserviceapi.dto.companies;

import com.targetmonkey.registrationserviceapi.enums.Status;
import lombok.Data;

@Data
public class CompanyUserDto {
    private long id;
    private String companyName;
    private String companyGId;
    private String category;
    private String description;
    private long leadsCounts;
    private Status status;
    private String moderationComment;
}
