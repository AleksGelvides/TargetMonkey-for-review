package com.targetmonkey.registrationserviceimpl.facade;

import com.targetmonkey.registrationserviceapi.dto.companies.CompanyAdminDto;
import com.targetmonkey.registrationserviceapi.dto.companies.CompanyUserDto;
import com.targetmonkey.registrationserviceapi.dto.customers.CustomerDto;
import com.targetmonkey.registrationserviceapi.dto.userinterfacesdto.CustomerViewDto;
import com.targetmonkey.registrationserviceapi.enums.Status;
import com.targetmonkey.registrationserviceimpl.mappers.CompanyMapper;
import com.targetmonkey.registrationserviceimpl.mappers.CustomersMapper;
import com.targetmonkey.registrationserviceimpl.service.CompanyServiceImp;
import com.targetmonkey.registrationserviceimpl.service.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class CustomerFacade {

    @Autowired
    private CustomerServiceImpl customerService;
    @Autowired
    private CompanyServiceImp companyServiceImp;

    //USER

    public CustomerViewDto getCustomerDtoFromUser(String username){
        var customerView =
                CustomersMapper.INSTANCE.toCustomerView(customerService.getByUserName(username));
        List<CompanyUserDto> companies = companyServiceImp.getAllByOwnerId(customerView.getId()).stream()
                .map(CompanyMapper.INSTANCE::toCompanyUserDto).toList();
        customerView.setCompanies(companies);
        return customerView;
    }

    public CustomerDto editCustomerDtoFromUser(String username, CustomerDto updatedCustomerDto){
        var updatedDto = customerService.editCustomer(username, updatedCustomerDto);
        return CustomersMapper.INSTANCE.toCustomerDto(updatedDto);
    }

    public CompanyUserDto createCompany(String username, CompanyUserDto companyUserDto){
        var companyAdminDto = CompanyMapper.INSTANCE.toCompanyAdminFromUser(companyUserDto);
        CompanyAdminDto result;
        companyAdminDto.setOwnerId(customerService.getByUserName(username).getId())
                .setCreated(new Date())
                .setUpdated(new Date())
                .setStatus(Status.FROZEN);
            result = companyServiceImp.createCompany(companyAdminDto);
            return CompanyMapper.INSTANCE.toCompanyUserDto(result);

    }

    public CompanyUserDto getCompanyByUsernameAndCompanyId(String username, long companyId){
        var customer = customerService.getByUserName(username);
        var response = companyServiceImp.
                getByOwnerIdAndCompanyId(customer.getId(), companyId);
        return CompanyMapper.INSTANCE.toCompanyUserDto(response);
    }

    public CompanyUserDto editCompany(long id, CompanyUserDto companyUserDto){
        var updatedCompany = companyServiceImp.editCompany(id, companyUserDto);
        return CompanyMapper.INSTANCE.toCompanyUserDto(updatedCompany);
    }

    public void deleteCompany(String username, long companyId){
        companyServiceImp.deleteCompany(customerService.getByUserName(username).getId(), companyId);
    }

}
