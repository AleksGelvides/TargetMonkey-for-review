package com.targetmonkey.registrationserviceimpl.resources.companies;

import com.targetmonkey.registrationserviceapi.dto.companies.CompanyAdminDto;
import com.targetmonkey.registrationserviceapi.resource.v1.CompanyAdminRestControllerV1;
import com.targetmonkey.registrationserviceimpl.facade.AdminFacade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.webjars.NotFoundException;

@Slf4j
@RestController
public class CompanyAdminRestControllerV1Impl implements CompanyAdminRestControllerV1 {
    @Autowired
    private AdminFacade facade;

    @Override
    public ResponseEntity<?> getAllCompanies() {
        return new ResponseEntity<>(facade.getAllCompanies(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getCompanyById(long companyId) {
        try{
            var response = facade.getCompanyById(companyId);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (NotFoundException e){
            log.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<?> editCompany(long companyId, CompanyAdminDto companyDto) {
        try{
            var response = facade.editCompany(companyId, companyDto);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> deleteCompany(long companyId) {
        try{
            facade.deleteCompany(companyId);
            return new ResponseEntity<>("Company was deleted", HttpStatus.OK);
        }catch (NotFoundException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
