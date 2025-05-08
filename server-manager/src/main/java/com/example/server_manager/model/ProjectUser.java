package com.example.server_manager.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.userdetails.User;

@Entity
@Table(name = "project_users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String roleInProject;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;


}