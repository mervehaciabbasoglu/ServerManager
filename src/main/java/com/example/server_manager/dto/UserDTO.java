package com.example.server_manager.dto;

import lombok.Data;

import java.util.Set;

@Data
public class UserDTO {
    private String username;
    private String password;
    private Set<String> roles;
}
