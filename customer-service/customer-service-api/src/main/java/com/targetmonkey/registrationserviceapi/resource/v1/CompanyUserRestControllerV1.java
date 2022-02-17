package com.targetmonkey.registrationserviceapi.resource.v1;

import com.targetmonkey.registrationserviceapi.dto.companies.CompanyUserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/users/companies/v1/")
public interface CompanyUserRestControllerV1 {

    @GetMapping("company/{companyId}")
    ResponseEntity<?> getCompanyById(HttpServletRequest request,
                                     @PathVariable long companyId);

    @PostMapping("create")
    ResponseEntity<?> createCompany(HttpServletRequest request,
                                    @RequestBody CompanyUserDto companyUserDto);

    @PutMapping("edit/{companyId}")
    ResponseEntity<?> editCompany(@PathVariable long companyId,
                                  @RequestBody CompanyUserDto companyUserDto);

    @DeleteMapping("delete/{companyId}")
    ResponseEntity<?> deleteCompany(HttpServletRequest request,
                                    @PathVariable long companyId);
}
