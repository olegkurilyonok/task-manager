package com.example.taskmanager.services;

import com.example.taskmanager.dtos.JWTRequest;
import com.example.taskmanager.dtos.JWTResponse;
import com.example.taskmanager.dtos.RegistrationUserDto;
import com.example.taskmanager.exceptions.AppError;
import com.example.taskmanager.utils.JWTokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserService userService;
    private final JWTokenUtils jwTokenUtils;
    private final AuthenticationManager authenticationManager;

    public ResponseEntity<?> createAuthToken(JWTRequest request) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(new AppError(HttpStatus.UNAUTHORIZED.value(), "Wrong login or password"), HttpStatus.UNAUTHORIZED);
        }
        UserDetails userDetails = userService.loadUserByUsername(request.getUsername());
        String token = jwTokenUtils.generateToken(userDetails);
        return ResponseEntity.ok(new JWTResponse(token));
    }

    public ResponseEntity<?> createNewUser(RegistrationUserDto request) {
        if (!request.getPassword().equals(request.getConfirmPassword())) {
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "Passwords not matched"), HttpStatus.BAD_REQUEST);
        }
        if (userService.findByUsername(request.getUsername()).isPresent()) {
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "Login is already taken"), HttpStatus.BAD_REQUEST);
        }
        userService.createUser(request);
        return ResponseEntity.ok().build();
    }
}
