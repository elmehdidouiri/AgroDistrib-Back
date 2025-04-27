package org.example.miniprojetback.Repositories;

import org.example.miniprojetback.Models.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}