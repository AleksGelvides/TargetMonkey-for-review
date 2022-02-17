package com.targetmonkey.registrationserviceimpl.security.configuration;

import com.targetmonkey.securitycommon.security.jwt.JwtTokenProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

@Slf4j
@ComponentScan("com.targetmonkey.securitycommon")
@Component
public class JwtTokenFilter extends GenericFilterBean {
    @Autowired
    private JwtTokenProvider provider;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {
        try {
            String token = provider.resolveToken((HttpServletRequest) request);
            if (token != null || provider.validateToken(token)) {
                var auth = provider.getAuthentication(token);
                if (auth != null)
                    SecurityContextHolder.getContext().setAuthentication(auth);
            }
            chain.doFilter(request, response);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
