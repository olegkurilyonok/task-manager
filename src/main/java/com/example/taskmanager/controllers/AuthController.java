package com.example.taskmanager.controllers;

import com.example.taskmanager.dtos.JWTRequest;
import com.example.taskmanager.dtos.RegistrationUserDto;
import com.example.taskmanager.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/auth")
    public ResponseEntity<?> createAuthToken(@RequestBody JWTRequest request) {
        return authService.createAuthToken(request);
    }

    @PostMapping("/registration")
    public ResponseEntity<?> createNewUser(@RequestBody RegistrationUserDto request) {
        return authService.createNewUser(request);
    }
}
