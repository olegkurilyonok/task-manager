package com.example.taskmanager.dtos;

import lombok.Data;

@Data
public class JWTRequest {
    private String username;
    private String password;
}
