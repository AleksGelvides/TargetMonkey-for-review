package resource;

import dto.CustomerAuthDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/v1/auth/")
public interface AuthenticationRestClientV1 {

    @PostMapping("login")
    ResponseEntity<?> login(@RequestBody CustomerAuthDto dto);

    @PostMapping("logout")
    public void logout(HttpServletRequest request, HttpServletResponse response);

}
