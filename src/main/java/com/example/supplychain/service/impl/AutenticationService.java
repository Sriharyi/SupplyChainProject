package com.example.supplychain.service.impl;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class AutenticationService {

    public String getToken(String username, String pwd) {
        Claims claim=Jwts.claims().setSubject(username);
        String token=Jwts.builder().setClaims(claim).signWith(SignatureAlgorithm.HS256, "secret").compact();
        System.out.println(token);
        return token;
    }

    public String extractToken(String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7); 
        }
        return null;
    }

    public boolean validateToken(String token,String username) {
        System.out.println( "token "+token);
        String tokengot=getToken(username,"");
        if(tokengot.equals(token))
            return true;
        return false;
    }
    
}