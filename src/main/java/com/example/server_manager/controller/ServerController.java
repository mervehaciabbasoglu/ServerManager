package com.example.server_manager.controller;

import com.example.server_manager.model.Server;
import com.example.server_manager.service.ServerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/servers")
@RequiredArgsConstructor
public class ServerController {

    private final ServerService serverService;

    @PostMapping
    public Server add(@RequestBody Server server) {
        return serverService.addServer(server);
    }

    @GetMapping
    public List<Server> list() {
        return serverService.getAllServers();
    }
}
