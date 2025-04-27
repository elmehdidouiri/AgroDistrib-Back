package org.example.miniprojetback.Services.impl;

import org.example.miniprojetback.Models.Commande;
import org.example.miniprojetback.Repositories.CommandeRepository;
import org.example.miniprojetback.Services.ICommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommandeServiceImpl implements ICommandeService {

    @Autowired
    private CommandeRepository commandeRepository;

    @Override
    public Commande createCommande(Commande commande) {
        return commandeRepository.save(commande);
    }

    @Override
    public Commande updateCommande(Long id, Commande commande) {
        Optional<Commande> existingCommande = commandeRepository.findById(id);
        if (existingCommande.isPresent()) {
            Commande updatedCommande = existingCommande.get();
            updatedCommande.setStatus(commande.getStatus());
            updatedCommande.setTotalPrice(commande.getTotalPrice());
            updatedCommande.setDateCommande(commande.getDateCommande());
            return commandeRepository.save(updatedCommande);
        }
        return null;  // Vous pouvez lancer une exception personnalisée si la commande n'existe pas
    }

    @Override
    public Optional<Commande> getCommandeById(Long id) {
        return commandeRepository.findById(id);
    }

    @Override
    public List<Commande> getAllCommandes() {
        return commandeRepository.findAll();
    }

    @Override
    public void deleteCommande(Long id) {
        commandeRepository.deleteById(id);
    }
}
