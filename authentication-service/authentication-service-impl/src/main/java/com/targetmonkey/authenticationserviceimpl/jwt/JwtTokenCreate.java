package com.targetmonkey.authenticationserviceimpl.jwt;

import com.targetmonkey.authenticationserviceimpl.exceptions.CustomerNotFoundException;
import com.targetmonkey.authenticationserviceimpl.service.CustomerServiceImpl;
import dto.CustomerAllDto;
import dto.CustomerAuthDto;
import dto.CustomerRegistrationDto;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
public class JwtTokenCreate {
    String secret = "123";
    private final CustomerServiceImpl customerService;

    @Autowired
    public JwtTokenCreate(CustomerServiceImpl customerService){
        this.customerService = customerService;
    }

    public String tokenCreate(CustomerAuthDto dto){
        CustomerAllDto customerAllDto = customerService.getCustomerByUserName(dto.username());
        var claims = Jwts.claims().setSubject(customerAllDto.getUsername());
        claims.put("roles", customerAllDto.getRoles());
        Date now = new Date();
        Date validityTime = new Date(now.getTime() + 100500);
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validityTime)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }
}
