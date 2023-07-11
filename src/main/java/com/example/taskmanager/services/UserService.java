package com.example.taskmanager.services;

import com.example.taskmanager.configs.EncryptedComponent;
import com.example.taskmanager.dtos.RegistrationUserDto;
import com.example.taskmanager.entites.User;
import com.example.taskmanager.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final RoleService roleService;
    private final UserRepository userRepository;
    private final EncryptedComponent encryptedComponent;


    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(
                String.format("User %s not found", username)
        ));

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName())).toList()
        );
    }

    public void createUser(RegistrationUserDto userDto) {
        User user = User.builder()
                .email(userDto.getEmail())
                .roles(List.of(roleService.getRole()))
                .password(encryptedComponent.bCryptPasswordEncoder().encode(userDto.getPassword()))
                .username(userDto.getUsername())
                .build();

        userRepository.save(user);
    }
}
