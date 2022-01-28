package com.targetmonkey.adclientregistrationlandings.feign;

import entities.AdCustomerRegistrationDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("data-rest-service")
public interface RegistrationFeignClient{

    @PostMapping("api/ad-customer.v1/save")
    String saveAdCustomer(@RequestBody AdCustomerRegistrationDTO customer);
}
