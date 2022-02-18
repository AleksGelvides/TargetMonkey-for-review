package com.targetmonkey.registrationserviceapi.resource.v1;

import com.targetmonkey.registrationserviceapi.dto.companies.CompanyAdminDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/admin/companies/v1/")
public interface CompanyAdminRestControllerV1 {

    @GetMapping("all")
    ResponseEntity<?> getAllCompanies();

    @GetMapping("company/{companyId}")
    ResponseEntity<?> getCompanyById(@PathVariable long companyId);

    @PutMapping("edit/{companyId}")
    ResponseEntity<?> editCompany(@PathVariable long companyId,
                                  @RequestBody CompanyAdminDto companyDto);

    @DeleteMapping("delete/{companyId}")
    ResponseEntity<?> deleteCompany(@PathVariable long companyId);
}
