package com.targetmonkey.registrationserviceimpl.facade;

import com.targetmonkey.registrationserviceapi.dto.companies.CompanyAdminDto;
import com.targetmonkey.registrationserviceapi.dto.customers.CustomerAdminDto;
import com.targetmonkey.registrationserviceimpl.service.CompanyServiceImp;
import com.targetmonkey.registrationserviceimpl.service.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AdminFacade {
    @Autowired
    private CustomerServiceImpl customerService;
    @Autowired
    private CompanyServiceImp companyService;

    public CustomerAdminDto getCustomerById(long id){
        return customerService.getToId(id);
    }

    public List<CustomerAdminDto> getAllCustomersFromAdmin(){
        return customerService.getAllCustomers();
    }

    public CustomerAdminDto editCustomerAdminFromAdmin(long id, CustomerAdminDto customerAdminDto){
        return customerService.editCustomer(id, customerAdminDto);
    }

    public void deleteCustomer(long id){
        customerService.deleteCustomer(id);
    }

    //Companies

    public List<CompanyAdminDto> getAllCompanies(){
        return companyService.getAllCompany();
    }

    public CompanyAdminDto getCompanyById(long id){
        return companyService.getCompanyById(id);
    }

    public CompanyAdminDto editCompany(long id, CompanyAdminDto adminDto){
        return companyService.editCompany(id, adminDto);
    }

    public void deleteCompany(long id){
        companyService.deleteCompany(id);
    }

}
