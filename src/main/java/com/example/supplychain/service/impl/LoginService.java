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
                "ur97q2e7r2934892rnu213rn09217349782190348y12").compact();
        return token;
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey("ur97q2e7r2934892rnu213rn09217349782190348y12").parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String extractToken(String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7);
        }
        return null;
    }
}