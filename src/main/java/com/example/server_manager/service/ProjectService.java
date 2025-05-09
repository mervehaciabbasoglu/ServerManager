package com.example.server_manager.service;

import com.example.server_manager.model.*;
import com.example.server_manager.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    public Project createProject(String name) {
        return projectRepository.save(Project.builder().name(name).build());
    }

    public void assignUserToProject(Long projectId, Long userId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        project.getUsers().add(user);
        projectRepository.save(project);
    }

    public List<Project> getAll() {
        return projectRepository.findAll();
    }
}
