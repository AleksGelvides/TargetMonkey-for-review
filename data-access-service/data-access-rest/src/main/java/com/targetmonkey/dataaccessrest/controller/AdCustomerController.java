package com.targetmonkey.dataaccessrest.controller;

import com.targetmonkey.dataserviceimpl.service.AdCustomerRegistrationService;
import entitiesDto.AdCustomerRegistrationDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/ad-customer.v1")
@ComponentScan("com.targetmonkey")
public class AdCustomerController {
    @Autowired
    AdCustomerRegistrationService adCustomerRegistrationService;

    @GetMapping("all")
    public List<AdCustomerRegistrationDTO> getAll(){
        return adCustomerRegistrationService.getAllCustomers();
    }

    @PostMapping("save")
    public String saveAdCustomer(@RequestBody AdCustomerRegistrationDTO adCustomerRegistrationDTO){
        adCustomerRegistrationService.saveAdCustomer(adCustomerRegistrationDTO);
        return "Success-adding-user";
    }
}
