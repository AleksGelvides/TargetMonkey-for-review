package com.targetmonkey.registrationserviceimpl.mappers;

import com.targetmonkey.registrationserviceapi.dto.companies.CompanyAdminDto;
import com.targetmonkey.registrationserviceapi.dto.companies.CompanyUserDto;
import com.targetmonkey.registrationserviceimpl.domain.CompanyJpa;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CompanyMapper {
    CompanyMapper INSTANCE = Mappers.getMapper(CompanyMapper.class);

    CompanyAdminDto toCompanyAdminDto(CompanyJpa companyJpa); //Из JPA в админа

    CompanyAdminDto toCompanyAdminFromUser(CompanyUserDto companyUserDto);

    CompanyJpa toCompanyJpa(CompanyAdminDto companyAdminDto); // Из Админа в JPA

    CompanyUserDto toCompanyUserDto(CompanyAdminDto companyAdminDto); // Из Админа в Кастомера

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "ownerId", ignore = true)
    @Mapping(target = "created", ignore = true)
    @Mapping(target = "updated", ignore = true)
    @Mapping(target = "leadsCounts", ignore = true)
    void updateCompanyAdminDTO(CompanyAdminDto newCompany, @MappingTarget CompanyAdminDto oldCompany); //Обновление: Админ/Админ

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "ownerId", ignore = true)
    @Mapping(target = "created", ignore = true)
    @Mapping(target = "updated", ignore = true)
    @Mapping(target = "leadsCounts", ignore = true)
    void updateCompanyUserDTO(CompanyUserDto newCompany, @MappingTarget CompanyAdminDto oldCompany);   //Обновление: Кастомер/Админ


}
