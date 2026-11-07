package com.telusko.spring_sec_demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.telusko.spring_sec_demo.dto.RequestRegister;
import com.telusko.spring_sec_demo.service.RegistrationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController 
@RequestMapping("/auth")
public class AuthController {

    @Autowired 
    RegistrationService service;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody RequestRegister request) {
        
        try {
            service.saveDataIntoDb(request);
            return ResponseEntity.status(HttpStatus.CREATED).body("User Registered");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }
    

}
