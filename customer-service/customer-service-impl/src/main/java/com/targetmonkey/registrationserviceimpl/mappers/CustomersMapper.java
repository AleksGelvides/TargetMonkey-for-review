package com.targetmonkey.registrationserviceimpl.mappers;

import com.targetmonkey.registrationserviceapi.dto.customers.CustomerAdminDto;
import com.targetmonkey.registrationserviceapi.dto.customers.CustomerDto;
import com.targetmonkey.registrationserviceapi.dto.userinterfacesdto.CustomerViewDto;
import com.targetmonkey.registrationserviceimpl.entity.CustomerJpa;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomersMapper {
    CustomersMapper INSTANCE = Mappers.getMapper(CustomersMapper.class);

    CustomerAdminDto toCustomerAdminDto(CustomerJpa customerJpa); //Из JPA в админа

    CustomerJpa toCustomerJpa(CustomerAdminDto customerAdminDto); // Из Админа в JPA

    CustomerDto toCustomerDto(CustomerAdminDto customerAdminDto); // Из Админа в Кастомера

    CustomerViewDto toCustomerView(CustomerAdminDto customerAdminDto);

    void updateCustomerDTO(CustomerAdminDto newCustomer, @MappingTarget CustomerAdminDto oldCustomer); //Обновление: Админ/Админ

    void updateCustomerDTO(CustomerDto newCustomer, @MappingTarget CustomerAdminDto oldCustomer);   //Обновление: Кастомер/Админ


}
