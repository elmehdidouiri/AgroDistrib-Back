package org.example.miniprojetback.Services;

import org.example.miniprojetback.Models.Commande;

import java.util.List;
import java.util.Optional;

public interface ICommandeService {

    Commande createCommande(Commande commande);

    Commande updateCommande(Long id, Commande commande);

    Optional<Commande> getCommandeById(Long id);

    List<Commande> getAllCommandes();

    void deleteCommande(Long id);
}
