package org.example.miniprojetback.Repositories;


import org.example.miniprojetback.Models.ProduitCommande;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProduitCommandeRepository extends JpaRepository<ProduitCommande, Long> {
}
