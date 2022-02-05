package com.targetmonkey.registrationserviceapi.v1;


import com.targetmonkey.registrationserviceapi.dto.AdCompanyRegistrationDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/ad-companies.v1/")
public interface AdClientCompanyRegistrationV1 {

    @PostMapping("save")
    String saveAdCompany(@RequestBody AdCompanyRegistrationDTO adCompanyRegistrationDTO);

    @GetMapping("company/{id}")
    AdCompanyRegistrationDTO getToId(@PathVariable long id);

    @GetMapping("all")
    List<AdCompanyRegistrationDTO> getAll();

    @PutMapping("edit/{id}")
    String editAdCompany(@PathVariable long id,
                          @RequestBody AdCompanyRegistrationDTO adCompanyRegistrationDTO);

    @DeleteMapping("delete/{id}")
    String deleteAdCompany(@PathVariable long id);
}
