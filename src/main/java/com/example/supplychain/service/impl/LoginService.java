package com.example.supplychain.service.impl;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class LoginService {
    public String generateToken(String username, String pwd) {
        Claims claims = Jwts.claims().setSubject(username);
        String token = Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS256,
                "*U(8hj908ns98daniasudfniawur97q2e7r2934892rnu213rn09217349782190348y12").compact();
        return token;
    }

    public String validateToken(String token, String name) {
        if (token.equals(generateToken(name, ""))) {
            return "Valid";
        }
        return "Invalid";
    }
}