package com.example.SecurityPractice.service;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class JwtService {

    public String generateToken(String username)
    {
        Map<String, Object> claims=new HashMap<>();

        return createToken(claims,username)
    }

    private String createToken(Map<String, Object> claims, String username) {

    }
}
