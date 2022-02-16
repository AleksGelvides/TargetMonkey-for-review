package com.targetmonkey.registrationserviceimpl.mappers;

import com.targetmonkey.registrationserviceapi.dto.customers.CustomerAdminDto;
import com.targetmonkey.registrationserviceapi.dto.customers.CustomerDto;
import com.targetmonkey.registrationserviceimpl.entity.CustomerJpa;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomersMapper {
    CustomersMapper INSTANCE = Mappers.getMapper(CustomersMapper.class);

    CustomerDto toCustomerDto(CustomerJpa customerJpa);

    CustomerAdminDto toCustomerAdminDto(CustomerJpa customerJpa);
}
