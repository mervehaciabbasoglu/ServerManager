
package com.example.server_manager.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "subdomains")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Subdomain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; // api.mentalhealth.com

    private String serviceType; // API, AI, vb.

    @ManyToOne
    @JoinColumn(name = "server_id")
    private Server server;


}

