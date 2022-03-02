package com.targetmonkey.authenticationserviceimpl.resource;

import com.targetmonkey.authenticationserviceimpl.service.CustomerServiceImpl;
import com.targetmonkey.securitycommon.security.jwt.JwtTokenProvider;
import dto.CustomerAuthDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import resource.AuthenticationRestClientV1;
import java.util.HashMap;
import java.util.Map;


@Slf4j
@ComponentScan("com.targetmonkey.securitycommon")
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
        try{
            authenticationManagerBean.authenticate(
                    new UsernamePasswordAuthenticationToken(dto.username(), dto.password()));
            String jwt = provider.createToken(customerService.loadUserByUsername(dto.username()));
            Map<String, String> response = new HashMap<>();
            response.put("username", dto.username());
            response.put("jwt", jwt);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            log.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
