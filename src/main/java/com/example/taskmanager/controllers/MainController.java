package com.example.taskmanager.controllers;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MainController {

    @GetMapping("/unsecure")
    public String unsecureData(){
        return "Unsecure data";
    }

    @GetMapping("/secure")
    public String secureData(){
        return "Secure data";
    }

    @GetMapping("/admin")
    public String adminData(){
        return "Admin data";
    }

    @GetMapping("/info")
    public String userData(){
        return "User data";
    }

}
