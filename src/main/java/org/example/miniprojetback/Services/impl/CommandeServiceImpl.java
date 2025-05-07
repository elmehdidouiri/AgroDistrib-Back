package org.example.miniprojetback.Services.impl;

import jakarta.transaction.Transactional;
import org.example.miniprojetback.DAOs.request.CommandeRequest;
import org.example.miniprojetback.DAOs.request.ProduitCommandeRequest;
import org.example.miniprojetback.DAOs.response.CommandeResponse;
import org.example.miniprojetback.Exceptions.ResourceNotFoundException;
import org.example.miniprojetback.Models.Commande;
import org.example.miniprojetback.Models.LigneCommande;
import org.example.miniprojetback.Models.Produit;
import org.example.miniprojetback.Models.enums.CommandeStatus;
import org.example.miniprojetback.Repositories.CommandeRepository;
import org.example.miniprojetback.Repositories.LigneCommandeRepository;
import org.example.miniprojetback.Repositories.ProduitRepository;
import org.example.miniprojetback.Services.ICommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommandeServiceImpl implements ICommandeService {

    @Autowired
    private CommandeRepository commandeRepository;

    @Autowired
    private LigneCommandeRepository ligneCommandeRepository;

    @Autowired
    private ProduitRepository produitRepository;

    @Transactional
    @Override
    public CommandeResponse createCommande(CommandeRequest request) {
        Commande commande = new Commande();
        commande.setClient(request.getClient());
        commande.setDateCommande(LocalDateTime.now());
        commande.setStatus(CommandeStatus.EN_COURS); // Utilisation de l'énumération
        Commande savedCommande = commandeRepository.save(commande);

        List<LigneCommande> lignesCommande = request.getProduits().stream().map(produitCommandeRequest -> {
            Produit produit = produitRepository.findById(produitCommandeRequest.getProduitId())
                    .orElseThrow(() -> new ResourceNotFoundException("Produit non trouvé avec l'ID : " + produitCommandeRequest.getProduitId()));

            LigneCommande ligneCommande = new LigneCommande();
            ligneCommande.setCommande(savedCommande);
            ligneCommande.setProduit(produit);
            ligneCommande.setQuantite(produitCommandeRequest.getQuantity());
            return ligneCommandeRepository.save(ligneCommande);
        }).collect(Collectors.toList());

        savedCommande.setLignesCommande(lignesCommande);
        return mapToCommandeResponse(savedCommande);
    }

    private CommandeResponse mapToCommandeResponse(Commande commande) {
        CommandeResponse response = new CommandeResponse();
        response.setId(commande.getId());
        response.setDateCommande(commande.getDateCommande());
        response.setStatus(commande.getStatus().name()); // Conversion de l'énumération en chaîne
        response.setProduits(commande.getLignesCommande().stream().map(ligne -> {
            ProduitCommandeRequest produitCommande = new ProduitCommandeRequest();
            produitCommande.setProduitId(ligne.getProduit().getId());
            produitCommande.setQuantity(ligne.getQuantite());
            return produitCommande;
        }).collect(Collectors.toList()));
        return response;
    }
    @Transactional
    @Override
    public CommandeResponse validerPaiement(Long commandeId) {
        Commande commande = commandeRepository.findById(commandeId)
                .orElseThrow(() -> new ResourceNotFoundException("Commande non trouvée avec l'ID : " + commandeId));
        commande.setPaiementValide(true);
        Commande savedCommande = commandeRepository.save(commande);
        return mapToCommandeResponse(savedCommande);
    }

    @Transactional
    @Override
    public CommandeResponse modifierStatutCommande(Long commandeId, String nouveauStatut) {
        Commande commande = commandeRepository.findById(commandeId)
                .orElseThrow(() -> new ResourceNotFoundException("Commande non trouvée avec l'ID : " + commandeId));

        try {
            CommandeStatus status = CommandeStatus.valueOf(nouveauStatut.toUpperCase());
            commande.setStatus(status);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Statut invalide : " + nouveauStatut);
        }

        commandeRepository.save(commande);
        return mapToCommandeResponse(commande);
    }

    @Override
    public List<CommandeResponse> getCommandesParSuperviseur(Long superviseurId) {
        List<Commande> commandes = commandeRepository.findBySuperviseurId(superviseurId);
        return commandes.stream().map(this::mapToCommandeResponse).collect(Collectors.toList());
    }

    @Override
    public List<CommandeResponse> getCommandesParClient(Long clientId) {
        List<Commande> commandes = commandeRepository.findByClientId(clientId);
        return commandes.stream().map(this::mapToCommandeResponse).collect(Collectors.toList());
    }
    @Override
    public List<CommandeResponse> getToutesLesCommandes() {
        List<Commande> commandes = commandeRepository.findAll();
        return commandes.stream().map(this::mapToCommandeResponse).collect(Collectors.toList());
    }


}