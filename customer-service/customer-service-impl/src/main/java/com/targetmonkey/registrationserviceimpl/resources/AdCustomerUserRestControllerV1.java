package com.targetmonkey.registrationserviceimpl.resources;

import com.targetmonkey.registrationserviceapi.dto.customers.CustomerDto;
import com.targetmonkey.registrationserviceapi.resource.v1.CustomerUserRestControllerV1;
import com.targetmonkey.registrationserviceimpl.facade.CustomerFacade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.webjars.NotFoundException;

@Slf4j
@RestController
public class AdCustomerUserRestControllerV1 implements CustomerUserRestControllerV1 {
    @Autowired
    private CustomerFacade customerFacade;

    @Override
    public ResponseEntity<?> getCustomerBuId(long id) {
        try{
            var customer = customerFacade.getCustomerDtoFromUser(id);
            return new ResponseEntity<>(customer, HttpStatus.OK);
        }catch (NotFoundException e){
            log.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<?> update(CustomerDto customerDto) {
        try{
            var updated = customerFacade.editCustomerDtoFromUser(customerDto);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        }catch (NotFoundException e){
            log.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
