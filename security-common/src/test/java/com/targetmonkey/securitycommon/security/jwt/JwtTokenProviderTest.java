package com.targetmonkey.securitycommon.security.jwt;

import com.targetmonkey.securitycommon.security.domain.Role;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@Slf4j
@SpringBootTest
@ActiveProfiles

public class JwtTokenProviderTest {

    @MockBean
    UserDetailsService detailsService;

    @Autowired
    @InjectMocks
    JwtTokenProvider provider;

    private UserDetails user;
    private String token;

    @BeforeEach
    public void initToken(){
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(Role.ROLE_CUSTOMER.name()));
        this.user = new User("TestUserName", "TestUserPassword",
                true, true, true, true, authorities);
        this.token = provider.createToken(user);
    }


    @Test
    public void createTokenTestMustReturnedJWTToken(){
        var token = provider.createToken(user);
        assertNotNull(token);
    }

    @Test
    public void resolveTokenTestMustReturnedStringWithJWTToken(){
        var correctToken = "Bearer_" + token;
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader("Authorization", correctToken);
        var response = provider.resolveToken(request);
        log.info("Test resolveToken: Token - " + response);
        assertNotNull(token);
        assertEquals(response, token);
    }

    @Test
    public void resolveTokenTestMustReturnedNull(){
        var notCorrectToken = token;
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader("Authorization", notCorrectToken);
        var response = provider.resolveToken(request);
        log.info("Test resolveToken: Token - " + response);
        assertNull(response);
    }

    @Test
    public void getUserNameMustReturnedUserName(){
        var username = provider.getUserName(token);
        assertNotNull(username);
        assertEquals(username, user.getUsername());
    }

    @Test
    public void getAuthenticationTestWasReturnedUsernamePasswordAuthenticationToken(){
        when(detailsService.loadUserByUsername(any())).thenReturn(user);
        var auth = provider.getAuthentication(token);
        assertNotNull(auth);
    }

    @Test
    public void validateTokenTestMustReturnedTrue(){
        boolean response = provider.validateToken(token);
        assertTrue(response);
    }

    @Test
    public void validateTokenTestMustReturnedFalse(){
        long validityTime = provider.getValidityInMilliseconds();
        provider.setValidityTimeOnlyForTests(-100000L);

        var invalidToken = provider.createToken(user);
        boolean response = provider.validateToken(invalidToken);
        assertFalse(response);

        provider.setValidityTimeOnlyForTests(validityTime);
    }
}
