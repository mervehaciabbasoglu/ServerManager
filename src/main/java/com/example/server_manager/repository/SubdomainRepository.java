package com.example.server_manager.repository;

import com.example.server_manager.model.Subdomain;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubdomainRepository extends JpaRepository<Subdomain, Long> {
}

