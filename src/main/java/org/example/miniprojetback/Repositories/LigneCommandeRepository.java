package org.example.miniprojetback.Repositories;

import org.example.miniprojetback.Models.LigneCommande;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LigneCommandeRepository extends JpaRepository<LigneCommande, Long> {
}