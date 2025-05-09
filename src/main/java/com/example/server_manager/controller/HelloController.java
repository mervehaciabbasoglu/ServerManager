package com.example.server_manager.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "Merhaba, sistem çalışıyor!";
    }
}
