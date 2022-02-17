package com.targetmonkey.securitycommon.security.jwt;

import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;

import javax.naming.AuthenticationException;

public class JwtAuthenticationException extends AuthenticationException {

    public JwtAuthenticationException(String explanation) {
        super(explanation);
    }

    public JwtAuthenticationException() {
    }
}
