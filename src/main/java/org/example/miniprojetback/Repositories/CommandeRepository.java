package org.example.miniprojetback.Repositories;

import org.example.miniprojetback.Models.Commande;
import org.example.miniprojetback.Models.Vendeur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommandeRepository extends JpaRepository<Commande, Long> {
    List<Commande> findBySuperviseurId(Long superviseurId);

    List<Commande> findByClientId(Long clientId);
    List<Commande> findByVendeur(Vendeur vendeur);
}