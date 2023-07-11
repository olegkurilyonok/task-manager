package com.example.taskmanager.controllers;

import com.example.taskmanager.dtos.JWTRequest;
import com.example.taskmanager.dtos.JWTResponse;
import com.example.taskmanager.exceptions.AppError;
import com.example.taskmanager.services.UserService;
import com.example.taskmanager.utils.JWTokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final JWTokenUtils JWTokenUtils;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/auth")
    public ResponseEntity<?> createAuthToken(@RequestBody JWTRequest request) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(new AppError(HttpStatus.UNAUTHORIZED.value(), "Wrong login or password"), HttpStatus.UNAUTHORIZED);
        }
        UserDetails userDetails = userService.loadUserByUsername(request.getUsername());
        String token = JWTokenUtils.generateToken(userDetails);
        return new ResponseEntity<>(new JWTResponse(token), HttpStatus.OK);
    }

    @PostMapping("/echo")
    public String testService(@RequestBody JWTRequest request) {
        return String.join(",", request.getUsername(), request.getPassword());
    }
}
