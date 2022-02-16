package com.targetmonkey.authenticationserviceimpl.resource;

import com.targetmonkey.authenticationserviceimpl.service.CustomerServiceImpl;
import com.targetmonkey.authenticationserviceimpl.serviceapi.CustomerService;
import com.targetmonkey.jwtcommon.security.jwt.JwtTokenProvider;
import dto.CustomerAuthDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import resource.AuthenticationRestClientV1;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@ComponentScan("com.targetmonkey.jwtcommon")
@RestController
@RequestMapping("/api/v1/auth/")
public class AuthRestClientV1 implements AuthenticationRestClientV1 {
    @Autowired
    private AuthenticationManager authenticationManagerBean;
    @Autowired
    private JwtTokenProvider provider;
    @Autowired
    CustomerServiceImpl customerService;



    @Override
    public ResponseEntity<?> login(CustomerAuthDto dto) {
        Map<String, String> response = new HashMap<>();
        try{
            authenticationManagerBean.authenticate(
                    new UsernamePasswordAuthenticationToken(dto.username(), dto.password()));
            String jwt = provider.createToken(customerService.loadUserByUsername(dto.username()));
            response.put("username", dto.username());
            response.put("jwt", jwt);
        }catch (Exception e){
            log.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        new SecurityContextLogoutHandler().logout(request, response, null);
    }
}
