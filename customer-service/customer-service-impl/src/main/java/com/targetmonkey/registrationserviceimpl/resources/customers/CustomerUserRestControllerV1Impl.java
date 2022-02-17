package com.targetmonkey.registrationserviceimpl.resources.customers;

import com.targetmonkey.registrationserviceapi.dto.customers.CustomerDto;
import com.targetmonkey.registrationserviceapi.resource.v1.CustomerUserRestControllerV1;
import com.targetmonkey.registrationserviceimpl.facade.CustomerFacade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.webjars.NotFoundException;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
public class CustomerUserRestControllerV1Impl implements CustomerUserRestControllerV1 {
    @Autowired
    private CustomerFacade customerFacade;

    @Override
    public ResponseEntity<?> getCustomerBuId(HttpServletRequest request) {
        try{
            var customer =
                    customerFacade.getCustomerDtoFromUser(request.getHeader("Username"));
            return new ResponseEntity<>(customer, HttpStatus.OK);
        }catch (NotFoundException e){
            log.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<?> update(HttpServletRequest request, CustomerDto customerDto) {
        try{
            var updated = customerFacade.editCustomerDtoFromUser(
                    request.getHeader("Username"), customerDto);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        }catch (NotFoundException e){
            log.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
