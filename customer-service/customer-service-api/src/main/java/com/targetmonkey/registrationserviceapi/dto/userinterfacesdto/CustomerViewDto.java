package com.targetmonkey.registrationserviceapi.dto.userinterfacesdto;

import com.targetmonkey.registrationserviceapi.dto.companies.CompanyUserDto;
import com.targetmonkey.registrationserviceapi.enums.Status;
import lombok.Data;

import java.util.List;

@Data
public class CustomerViewDto {
    private long id;
    private String name;
    private String surname;
    private String username;
    private String email;
    private List<CompanyUserDto> companies;
    private Status status;
}
