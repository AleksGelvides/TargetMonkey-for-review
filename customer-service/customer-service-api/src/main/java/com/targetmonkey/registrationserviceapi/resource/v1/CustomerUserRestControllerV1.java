package com.targetmonkey.registrationserviceapi.resource.v1;

import com.targetmonkey.registrationserviceapi.dto.customers.CustomerDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/api/users/customers/v1/")
public interface CustomerUserRestControllerV1 {

    @GetMapping("customer/")
    ResponseEntity<?> getCustomerBuId(HttpServletRequest request);

    @PutMapping("update")
    ResponseEntity<?> update(HttpServletRequest request,
                             @RequestBody CustomerDto customerDto);

}
