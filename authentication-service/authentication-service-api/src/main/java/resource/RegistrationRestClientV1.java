package resource;

import dto.CustomerRegistrationDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/registration/")
public interface RegistrationRestClientV1 {

    @PostMapping("create-customer")
    ResponseEntity<?> saveCustomer(@RequestBody CustomerRegistrationDto customerRegistrationDto);
}
