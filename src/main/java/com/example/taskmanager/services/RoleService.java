package com.example.taskmanager.services;

import com.example.taskmanager.entites.Role;
import com.example.taskmanager.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public Role getRole() {
        return roleRepository.findByName("ROLE_USER").get();
    }
}
