package com.targetmonkey.web.controllers;

import entitiesDto.AdCustomerRegistrationDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
public class Registration {

    @GetMapping("")
    public String goLanding(Model model){
        model.addAttribute("newAdCustomer", new AdCustomerRegistrationDTO());
        return "landings/first-landing/index";
    }

    @PostMapping("")
    public String successRegistration(@RequestBody AdCustomerRegistrationDTO AdCustomerRegistrationDTO){
        return "landings/first-landing/success/success";
    }
}
