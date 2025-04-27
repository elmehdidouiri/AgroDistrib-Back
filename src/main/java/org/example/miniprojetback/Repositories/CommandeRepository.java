package org.example.miniprojetback.Repositories;

import org.example.miniprojetback.Models.Commande;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommandeRepository extends JpaRepository<Commande, Long> {
}
