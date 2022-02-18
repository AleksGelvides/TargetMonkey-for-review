package com.targetmonkey.registrationserviceimpl.resources.customers;

import com.targetmonkey.registrationserviceapi.dto.customers.CustomerAdminDto;
import com.targetmonkey.registrationserviceapi.resource.v1.CustomerAdminRestControllerV1;
import com.targetmonkey.registrationserviceimpl.facade.AdminFacade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.webjars.NotFoundException;

import java.util.List;

@Slf4j
@RestController
public class CustomerAdminRestControllerV1Impl implements CustomerAdminRestControllerV1 {
    @Autowired
    private AdminFacade adminFacade;

    @Override
    public ResponseEntity<?> getAllCustomers() {
        try{
            List<CustomerAdminDto> responseList = adminFacade.getAllCustomersFromAdmin();
            return new ResponseEntity<>(responseList, HttpStatus.OK);
        } catch (Exception e){
            log.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> getCustomerBuId(long id) {
        try{
            var customer = adminFacade.getCustomerById(id);
            return new ResponseEntity<>(customer, HttpStatus.OK);
        }catch (NotFoundException e){
            log.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<?> update(long id, CustomerAdminDto customerAdminDto) {
        try{
            var updated = adminFacade.editCustomerAdminFromAdmin(id, customerAdminDto);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        }catch (Exception e){
            log.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<?> deleteCustomer(long id) {
        try{
            adminFacade.deleteCustomer(id);
            return new ResponseEntity<>("Customer removed successfully", HttpStatus.OK);
        }catch (NotFoundException e){
            log.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
