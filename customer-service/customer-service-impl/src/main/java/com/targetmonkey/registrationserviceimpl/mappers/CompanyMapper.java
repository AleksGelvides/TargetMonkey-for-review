package com.targetmonkey.registrationserviceimpl.mappers;

import com.targetmonkey.registrationserviceapi.dto.companies.CompanyAdminDto;
import com.targetmonkey.registrationserviceapi.dto.companies.CompanyUserDto;
import com.targetmonkey.registrationserviceimpl.domain.CompanyJpa;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CompanyMapper {
    CompanyMapper INSTANCE = Mappers.getMapper(CompanyMapper.class);

    CompanyAdminDto toCompanyAdminDto(CompanyJpa companyJpa); //Из JPA в админа

    CompanyAdminDto toCompanyAdminFromUser(CompanyUserDto companyUserDto);

    CompanyJpa toCompanyJpa(CompanyAdminDto companyAdminDto); // Из Админа в JPA

    CompanyUserDto toCompanyUserDto(CompanyAdminDto companyAdminDto); // Из Админа в Кастомера

    void updateCompanyAdminDTO(CompanyAdminDto newCompany, @MappingTarget CompanyAdminDto oldCompany); //Обновление: Админ/Админ

    void updateCompanyUserDTO(CompanyUserDto newCompany, @MappingTarget CompanyAdminDto oldCompany);   //Обновление: Кастомер/Админ


}
