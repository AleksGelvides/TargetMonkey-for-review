package com.targetmonkey.registrationserviceapi.resource.v1;

import com.targetmonkey.registrationserviceapi.dto.customers.CustomerAdminDto;
import com.targetmonkey.registrationserviceapi.dto.customers.CustomerDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/admin/customers/v1/")
public interface CustomerAdminRestControllerV1 {

    @GetMapping("all")
    ResponseEntity<?> getAllCustomers();

    @GetMapping("customer/{id}")
    ResponseEntity<?> getCustomerBuId(@PathVariable long id);

    @PutMapping("update/{id}")
    ResponseEntity<?> update(@PathVariable long id,
                             @RequestBody CustomerAdminDto customerAdminDto);

    @DeleteMapping("delete/{id}")
    ResponseEntity<?> deleteCustomer(@PathVariable long id);

}
