package com.targetmonkey.web.controllers;

import entities.AdCustomer;
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
        model.addAttribute("newAdCustomer", new AdCustomer());
        return "landings/first-landing/index";
    }

    @PostMapping("")
    public String successRegistration(@RequestBody AdCustomer adCustomer){
        return "landings/first-landing/success/success";
    }
}
