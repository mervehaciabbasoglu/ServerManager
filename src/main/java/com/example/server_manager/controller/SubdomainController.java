package com.example.server_manager.controller;

import com.example.server_manager.model.Subdomain;
import com.example.server_manager.service.SubdomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subdomains")
@RequiredArgsConstructor
public class SubdomainController {

    private final SubdomainService subdomainService;

    @PostMapping("/add/{serverId}")
    public Subdomain add(@PathVariable Long serverId, @RequestBody Subdomain subdomain) {
        return subdomainService.addSubdomainToServer(serverId, subdomain);
    }

    @GetMapping
    public List<Subdomain> list() {
        return subdomainService.getAll();
    }
}

