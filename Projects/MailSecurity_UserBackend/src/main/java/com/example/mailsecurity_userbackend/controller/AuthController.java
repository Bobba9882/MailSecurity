package com.example.mailsecurity_userbackend.controller;

import com.example.mailsecurity_userbackend.service.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController()
public class AuthController {
    private final TokenService tokenService;

    public AuthController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @PostMapping(value = "/token")
    public String token(Authentication authentication) {
        String token = tokenService.generateToken(authentication);
        return "{\"text\":\"" +token+ "\"}";
    }
}
