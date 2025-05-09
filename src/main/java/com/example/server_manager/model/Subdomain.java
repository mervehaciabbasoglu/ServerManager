package com.example.server_manager.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Subdomain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; // api.example.com

    private String description;

    private boolean isPublic;

    private String services; // örn: API, AI vs.

    @ManyToOne
    @JoinColumn(name = "server_id")
    private Server server;
}
