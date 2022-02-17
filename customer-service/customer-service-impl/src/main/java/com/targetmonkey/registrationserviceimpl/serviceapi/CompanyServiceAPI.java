package com.targetmonkey.registrationserviceimpl.serviceapi;

import com.targetmonkey.registrationserviceapi.dto.companies.CompanyAdminDto;
import com.targetmonkey.registrationserviceapi.dto.companies.CompanyUserDto;

import java.util.List;

public interface CompanyServiceAPI {

    List<CompanyAdminDto> getAllCompany();

    List<CompanyAdminDto> getAllByOwnerId(long id);

    CompanyAdminDto getCompanyById(long id);

    CompanyAdminDto getByOwnerIdAndCompanyId(long customerId, long companyId);

    CompanyAdminDto createCompany(CompanyAdminDto companyAdminDto);

    CompanyAdminDto editCompany(long id, CompanyAdminDto companyAdminDto);

    CompanyAdminDto editCompany(long id, CompanyUserDto companyUserDto);

    void deleteCompany(long ownerId, long id);

    void deleteCompany(long id);


}
