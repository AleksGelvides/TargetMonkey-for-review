package com.targetmonkey.registrationserviceapi.resource.v1;

import com.targetmonkey.registrationserviceapi.dto.customers.CustomerDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/users/customers/v1/")
public interface CustomerUserRestControllerV1 {

    @GetMapping("customer/{id}")
    ResponseEntity<?> getCustomerBuId(@PathVariable long id);

    @PutMapping("update")
    ResponseEntity<?> update(@RequestBody CustomerDto customerDto);

}
