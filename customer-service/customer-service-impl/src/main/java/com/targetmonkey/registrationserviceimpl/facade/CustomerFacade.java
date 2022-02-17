package com.targetmonkey.registrationserviceimpl.facade;

import com.targetmonkey.registrationserviceapi.dto.companies.CompanyUserDto;
import com.targetmonkey.registrationserviceapi.dto.customers.CustomerAdminDto;
import com.targetmonkey.registrationserviceapi.dto.customers.CustomerDto;
import com.targetmonkey.registrationserviceapi.dto.userinterfacesdto.CustomerViewDto;
import com.targetmonkey.registrationserviceapi.enums.Status;
import com.targetmonkey.registrationserviceimpl.mappers.CompanyMapper;
import com.targetmonkey.registrationserviceimpl.mappers.CustomersMapper;
import com.targetmonkey.registrationserviceimpl.service.CompanyServiceImp;
import com.targetmonkey.registrationserviceimpl.service.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.webjars.NotFoundException;

import java.util.Date;
import java.util.List;

@Component
public class CustomerFacade {

    @Autowired
    private CustomerServiceImpl customerService;
    @Autowired
    private CompanyServiceImp companyServiceImp;

    //USER

    public CustomerViewDto getCustomerDtoFromUser(long id){
        var customerView = CustomersMapper.INSTANCE.toCustomerView(customerService.getToId(id));
        List<CompanyUserDto> companies = companyServiceImp.getAllByOwnerId(id).stream()
                .map(CompanyMapper.INSTANCE::toCompanyUserDto).toList();
        customerView.setCompanies(companies);
        return customerView;
    }

    public CustomerDto editCustomerDtoFromUser(CustomerDto customerDto){
        var updatedDto = customerService.editCustomer(customerDto.getId(), customerDto);
        return CustomersMapper.INSTANCE.toCustomerDto(updatedDto);
    }

    public CompanyUserDto createCompany(long idCustomer, CompanyUserDto companyUserDto){
        var companyAdminDto = CompanyMapper.INSTANCE.toCompanyAdminFromUser(companyUserDto);
        companyAdminDto.setOwnerId(idCustomer)
                .setOwnerId(idCustomer)
                .setCreated(new Date())
                .setUpdated(new Date())
                .setStatus(Status.ACTIVE);
        var response = companyServiceImp.createCompany(companyAdminDto);
        return CompanyMapper.INSTANCE.toCompanyUserDto(companyAdminDto);
    }

    public CompanyUserDto getCompanyByOwnerIdAndCompanyId(long ownerId,long companyId){
        var response = companyServiceImp.getByCompanyIdAndCustomerId(ownerId, companyId);
        return CompanyMapper.INSTANCE.toCompanyUserDto(response);
    }

    public CompanyUserDto editCompany(long ownerId, long companyId, CompanyUserDto companyUserDto){
        var updatedCompany = companyServiceImp
                .getByCompanyIdAndCustomerId(ownerId, companyId);
        updatedCompany = companyServiceImp.editCompany(companyId, companyUserDto);
        return CompanyMapper.INSTANCE.toCompanyUserDto(updatedCompany);
    }

    public void deleteCompany(long ownerId, long companyId){
        companyServiceImp.deleteCompany(ownerId, companyId);
    }

    //ADMIN

    public CustomerAdminDto getCustomerDtoFromAdmin(long id){
        return customerService.getToId(id);
    }

    public List<CustomerAdminDto> getAllCustomersFromAdmin(){
        return customerService.getAllCustomers();
    }

    public CustomerAdminDto editCustomerAdminFromAdmin(CustomerAdminDto customerAdminDto){
        return customerService.editCustomer(customerAdminDto.getId(), customerAdminDto);
    }

    public void deleteCustomer(long id){
        customerService.deleteCustomer(id);
    }

}
