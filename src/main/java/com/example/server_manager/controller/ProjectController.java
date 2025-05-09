package com.example.server_manager.controller;

import com.example.server_manager.model.Project;
import com.example.server_manager.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping
    public Project create(@RequestParam String name) {
        return projectService.createProject(name);
    }

    @PostMapping("/{projectId}/assign")
    public void assignUser(@PathVariable Long projectId, @RequestParam Long userId) {
        projectService.assignUserToProject(projectId, userId);
    }

    @GetMapping
    public List<Project> list() {
        return projectService.getAll();
    }
}
