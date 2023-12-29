package com.example.supplychain.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.supplychain.service.impl.LoginService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(path = "/login")
public class LoginController {
    @Autowired
    LoginService service;

    @PostMapping("/{username}/{pwd}")
    String login(@PathVariable String username, @PathVariable String pwd) {
        return service.generateToken(username, pwd);
    }
}