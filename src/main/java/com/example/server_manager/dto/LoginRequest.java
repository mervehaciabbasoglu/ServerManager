package com.example.server_manager.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}
