package com.targetmonkey.registrationserviceimpl.mappers;

import com.targetmonkey.registrationserviceapi.dto.customers.CustomerAdminDto;
import com.targetmonkey.registrationserviceapi.dto.customers.CustomerDto;
import com.targetmonkey.registrationserviceapi.dto.userinterfacesdto.CustomerViewDto;
import com.targetmonkey.registrationserviceimpl.domain.CustomerJpa;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomersMapper {
    CustomersMapper INSTANCE = Mappers.getMapper(CustomersMapper.class);

    CustomerAdminDto toCustomerAdminDto(CustomerJpa customerJpa); //Из JPA в админа

    CustomerJpa toCustomerJpa(CustomerAdminDto customerAdminDto); // Из Админа в JPA

    CustomerDto toCustomerDto(CustomerAdminDto customerAdminDto); // Из Админа в Кастомера

    CustomerViewDto toCustomerView(CustomerAdminDto customerAdminDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "updated", ignore = true)
    @Mapping(target = "created", ignore = true)
    @Mapping(target = "username", ignore = true)
    void updateCustomerJpa(CustomerDto updatedUserCustomer, @MappingTarget CustomerJpa customerJpa);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "updated", ignore = true)
    @Mapping(target = "created", ignore = true)
    @Mapping(target = "username", ignore = true)
    void updateCustomerJpa(CustomerAdminDto updatedUserCustomer, @MappingTarget CustomerJpa customerJpa);

}
