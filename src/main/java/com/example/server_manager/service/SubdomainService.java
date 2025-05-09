package com.example.server_manager.service;


import com.example.server_manager.model.*;
import com.example.server_manager.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubdomainService {

    private final SubdomainRepository subdomainRepository;
    private final ServerRepository serverRepository;

    public Subdomain addSubdomainToServer(Long serverId, Subdomain subdomain) {
        Server server = serverRepository.findById(serverId)
                .orElseThrow(() -> new RuntimeException("Server not found"));
        subdomain.setServer(server);
        return subdomainRepository.save(subdomain);
    }

    public List<Subdomain> getAll() {
        return subdomainRepository.findAll();
    }
}

