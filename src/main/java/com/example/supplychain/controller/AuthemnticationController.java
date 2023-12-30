package com.example.supplychain.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.supplychain.service.impl.AutenticationService;

@RestController
@RequestMapping(path="auth")
public class AuthemnticationController {

    @Autowired
    AutenticationService loginService;
 @PostMapping("/{username}/{pwd}")
    public String login(@PathVariable String username, @PathVariable String pwd){
        return loginService.getToken(username,pwd);  
    }
    @GetMapping("/validate/{username}")
    public ResponseEntity<String> tokenValidation(@RequestHeader("Authorization") String authorizationHeader,@PathVariable String username) {
        String token=loginService.extractToken(authorizationHeader);

        if (loginService.validateToken(token,username)) {
            return ResponseEntity.ok("Valid token");
        } 
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
    }  

    @ExceptionHandler(value=RuntimeException.class)
     public ResponseEntity<?> runtimeExeHandle(){
        return new ResponseEntity<>("Runtime Exception occured", HttpStatus.BAD_REQUEST);
    }

     @ExceptionHandler(value=IllegalArgumentException.class)
     public ResponseEntity<?> IllegalArgumentException(){
        return new ResponseEntity<>("Illegal Argument Exception occured", HttpStatus.BAD_REQUEST);
    }
}