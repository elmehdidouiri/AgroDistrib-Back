package org.example.miniprojetback.Repositories;

import org.example.miniprojetback.Models.Produit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProduitRepository extends JpaRepository<Produit, Long> {
}
