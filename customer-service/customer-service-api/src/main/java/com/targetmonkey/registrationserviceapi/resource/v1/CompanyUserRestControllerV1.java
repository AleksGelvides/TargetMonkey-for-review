package com.targetmonkey.registrationserviceapi.resource.v1;

import com.targetmonkey.registrationserviceapi.dto.companies.CompanyUserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user/{ownerId}/companies/v1/")
public interface CompanyUserRestControllerV1 {

    @GetMapping("company/{companyId}")
    ResponseEntity<?> getCompanyById(@PathVariable long ownerId,
                                     @PathVariable long companyId);

    @PostMapping("create")
    ResponseEntity<?> createCompany(@PathVariable long ownerId,
                                    @RequestBody CompanyUserDto companyUserDto);

    @PutMapping("edit/{companyId}")
    ResponseEntity<?> editCompany(@PathVariable long ownerId,
                                  @PathVariable long companyId,
                                  @RequestBody CompanyUserDto companyUserDto);

    @DeleteMapping("delete/{companyId}")
    ResponseEntity<?> deleteCompany(@PathVariable long ownerId,
                                    @PathVariable long companyId);
}
