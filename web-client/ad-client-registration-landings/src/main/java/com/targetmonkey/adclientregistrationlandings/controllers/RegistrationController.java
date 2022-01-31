package com.targetmonkey.adclientregistrationlandings.controllers;

import com.targetmonkey.adclientregistrationlandings.entityMvc.AdCustomerRegistrationMvc;
import com.targetmonkey.adclientregistrationlandings.service.AdCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/registration/")
public class RegistrationController {
    @Autowired
    private AdCustomerService adCustomerService;

    @GetMapping("")
    public String registrationAdCustomer(Model model){
        model.addAttribute("newAdCustomer", new AdCustomerRegistrationMvc());
        return "index";
    }

    @PostMapping("save")
    public String successRegistration(@ModelAttribute("newAdCustomer")
                                          @Valid AdCustomerRegistrationMvc customer,
                                      BindingResult bindingResult){
        System.out.println(customer);
        if(bindingResult.hasErrors())
            return "index";
        adCustomerService.saveAdCustomer(customer);
        return "success-page";
    }
}
