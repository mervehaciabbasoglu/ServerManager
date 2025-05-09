package com.example.server_manager.service;

import com.example.server_manager.dto.*;
import com.example.server_manager.model.*;
import com.example.server_manager.repository.*;
import com.example.server_manager.security.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    public JwtResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        String token = jwtUtils.generateJwtToken(request.getUsername());
        return new JwtResponse(token, request.getUsername());
    }

    public String register(UserDTO dto) {
        if (userRepository.existsByUsername(dto.getUsername())) {
            return "Username already exists.";
        }

        Set<Role> roles = dto.getRoles().stream()
                .map(roleName -> roleRepository.findByName(roleName)
                        .orElseThrow(() -> new RuntimeException("Role not found: " + roleName)))
                .collect(java.util.stream.Collectors.toSet());

        User user = User.builder()
                .username(dto.getUsername())
                .password(passwordEncoder.encode(dto.getPassword()))
                .roles(roles)
                .build();

        userRepository.save(user);
        return "User registered successfully.";
    }
}
