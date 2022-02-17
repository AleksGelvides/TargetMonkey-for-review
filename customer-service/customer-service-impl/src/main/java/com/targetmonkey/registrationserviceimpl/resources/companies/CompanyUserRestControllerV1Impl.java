package com.targetmonkey.registrationserviceimpl.resources.companies;


import com.targetmonkey.registrationserviceapi.dto.companies.CompanyUserDto;
import com.targetmonkey.registrationserviceapi.resource.v1.CompanyUserRestControllerV1;
import com.targetmonkey.registrationserviceimpl.facade.CustomerFacade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.webjars.NotFoundException;

@Slf4j
@RestController
public class CompanyUserRestControllerV1Impl implements CompanyUserRestControllerV1 {
    @Autowired
    private CustomerFacade customerFacade;

    @Override
    public ResponseEntity<?> getCompanyById(long ownerId, long companyId) {
        try{
            var company = customerFacade.getCompanyByOwnerIdAndCompanyId(ownerId, companyId);
            return new ResponseEntity<>(company, HttpStatus.OK);
        }catch (NotFoundException e){
            log.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<?> createCompany(long ownerId, CompanyUserDto companyUserDto) {
        try{
            var response = customerFacade.createCompany(ownerId, companyUserDto);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            log.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> editCompany(long ownerId,
                                         long companyId,
                                         CompanyUserDto companyUserDto) {
        try{
            var response = customerFacade.editCompany(ownerId, companyId, companyUserDto);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (NotFoundException e){
            log.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (IllegalArgumentException e){
            log.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<?> deleteCompany(long ownerId, long companyId) {
        try{
            customerFacade.deleteCompany(ownerId, companyId);
            return new ResponseEntity<>("Company removed successfully", HttpStatus.OK);
        }catch (NotFoundException e){
            log.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
