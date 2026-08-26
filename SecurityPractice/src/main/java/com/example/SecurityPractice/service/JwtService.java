package com.example.SecurityPractice.service;

import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import java.util.Date;
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
       return Jwts.builder()
               .setClaims(claims)
               .setSubject(username)
               .setIssuedAt(new Date(System.currentTimeMillis()))
               .setExpiration(new Date(System.currentTimeMillis()+1000*60*30))
               .signWith(getSignKey())
    }
}
