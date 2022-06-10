package com.will.bookstoreapi.auth.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

import javax.crypto.SecretKey;

public class JwtFilterConfigurer extends AbstractHttpConfigurer<JwtFilterConfigurer, HttpSecurity> {

    private final SecretKey secretKey;
    private final JwtConfig jwtConfig;

    public JwtFilterConfigurer(SecretKey secretKey, JwtConfig jwtConfig) {
        this.secretKey = secretKey;
        this.jwtConfig = jwtConfig;
    }


    @Override
    public void configure(HttpSecurity http) throws Exception {
        AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
        http.addFilter(new JwtUsernameAndPasswordAuthFilter(authenticationManager,jwtConfig,secretKey))
                .addFilterAfter(new JwtTokenVerifier(secretKey, jwtConfig),JwtUsernameAndPasswordAuthFilter.class);
    }
    public static JwtFilterConfigurer jwtFilterConfigurer(SecretKey secretKey,JwtConfig jwtConfig){
        return new JwtFilterConfigurer(secretKey, jwtConfig);
    }
}