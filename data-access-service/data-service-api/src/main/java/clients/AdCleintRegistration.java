package clients;

import dtoentities.AdCustomerRegistrationDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ad-customers.v1/")
public interface AdCleintRegistration {

    @PostMapping("save")
    String saveAdCustomer(@RequestBody AdCustomerRegistrationDTO adCustomerRegistrationDTO);

    @GetMapping("all")
    List<AdCustomerRegistrationDTO> getAll();

    @GetMapping("customer/{id}")
    AdCustomerRegistrationDTO getToId(@PathVariable long id);

    @PutMapping("edit/{id}")
    String editAdCustomer(@PathVariable long id,
                          @RequestBody AdCustomerRegistrationDTO adCustomerRegistrationDTO);

    @DeleteMapping("delete/{id}")
    String deleteAdCustomer(@PathVariable long id);
}
