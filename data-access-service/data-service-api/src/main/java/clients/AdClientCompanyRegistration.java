package clients;


import dtoentities.AdCompanyRegistrationDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/ad-companies.v1/")
public interface AdClientCompanyRegistration {

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
