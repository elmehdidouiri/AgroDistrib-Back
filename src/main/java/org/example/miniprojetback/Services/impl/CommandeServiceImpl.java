package org.example.miniprojetback.Services.impl;

import jakarta.transaction.Transactional;
import org.example.miniprojetback.DAOs.request.CommandeRequest;
import org.example.miniprojetback.DAOs.request.ProduitCommandeRequest;
import org.example.miniprojetback.DAOs.response.CommandeResponse;
import org.example.miniprojetback.Exceptions.ResourceNotFoundException;
import org.example.miniprojetback.Models.*;
import org.example.miniprojetback.Models.enums.CommandeStatus;
import org.example.miniprojetback.Repositories.*;
import org.example.miniprojetback.Services.ICommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static org.example.miniprojetback.Models.enums.CommandeStatus.TERMINE;

@Service
public class CommandeServiceImpl implements ICommandeService {

    @Autowired
    private CommandeRepository commandeRepository;

    @Autowired
    private LigneCommandeRepository ligneCommandeRepository;

    @Autowired
    private ProduitRepository produitRepository;

    @Autowired
    private VendeurRepository vendeurRepository;

    @Autowired
    private SuperviseurRepository superviseurRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Transactional
    @Override
    public CommandeResponse createCommande(CommandeRequest request) {
        Commande commande = new Commande();

        // Récupérer le client à partir de l'ID
        Client client = clientRepository.findById(request.getClient())
                .orElseThrow(() -> new ResourceNotFoundException("Client non trouvé avec l'ID : " + request.getClient()));
        commande.setClient(client);
        client.setPoints(client.getPoints() + 1);

        commande.setDateCommande(LocalDateTime.now());
        commande.setStatus(CommandeStatus.EN_COURS);

        // Récupérer le vendeur
        Vendeur vendeur = vendeurRepository.findById(request.getVendeurId())
                .orElseThrow(() -> new ResourceNotFoundException("Vendeur non trouvé avec l'ID : " + request.getVendeurId()));
        commande.setVendeur(vendeur);

        // Récupérer le superviseur
        Superviseur superviseur = superviseurRepository.findById(request.getSuperviseurId())
                .orElseThrow(() -> new ResourceNotFoundException("Superviseur non trouvé avec l'ID : " + request.getSuperviseurId()));
        commande.setSuperviseur(superviseur);

        Commande savedCommande = commandeRepository.save(commande);

        List<LigneCommande> lignesCommande = request.getProduits().stream().map(produitCommandeRequest -> {
            Produit produit = produitRepository.findById(produitCommandeRequest.getProduitId())
                    .orElseThrow(() -> new ResourceNotFoundException("Produit non trouvé avec l'ID : " + produitCommandeRequest.getProduitId()));
            int nouvelleQuantite = produit.getQuantite() - produitCommandeRequest.getQuantity();
            if (nouvelleQuantite < 0) {
                throw new IllegalStateException("Stock insuffisant pour le produit : " + produit.getNom());
            }
            produit.setQuantite(nouvelleQuantite);
            produitRepository.save(produit);
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
        response.setStatus(String.valueOf(commande.getStatus()));
        response.setProduits(
                commande.getLignesCommande().stream()
                        .map(ligneCommande -> {
                            ProduitCommandeRequest produitRequest = new ProduitCommandeRequest();
                            produitRequest.setProduitId(ligneCommande.getProduit().getId());
                            produitRequest.setNomProduit(ligneCommande.getProduit().getNom());
                            produitRequest.setQuantity(ligneCommande.getQuantite());
                            produitRequest.setPrixUnitaire(ligneCommande.getProduit().getPrix()); // Ajout du prix unitaire
                            return produitRequest;
                        })
                        .collect(Collectors.toList())
        );
        response.setClientNom(commande.getClient().getName());
        response.setClientAdresse(commande.getClient().getAdresse());
        return response;
    }
    @Transactional
    @Override
    public CommandeResponse validerPaiement(Long commandeId) {
        Commande commande = commandeRepository.findById(commandeId)
                .orElseThrow(() -> new ResourceNotFoundException("Commande non trouvée avec l'ID : " + commandeId));
        commande.setPaiementValide(true);
        commande.setStatus(TERMINE);
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
    @Transactional
    @Override
    public List<CommandeResponse> getCommandesParSuperviseur(Long superviseurId) {
        List<Commande> commandes = commandeRepository.findBySuperviseurId(superviseurId);
        return commandes.stream().map(this::mapToCommandeResponse).collect(Collectors.toList());
    }
    @Transactional
    @Override
    public List<CommandeResponse> getCommandesParClient(Long clientId) {
        List<Commande> commandes = commandeRepository.findByClientId(clientId);
        return commandes.stream().map(this::mapToCommandeResponse).collect(Collectors.toList());
    }
    @Transactional
    @Override
    public List<CommandeResponse> getToutesLesCommandes() {
        List<Commande> commandes = commandeRepository.findAll();
        return commandes.stream().map(this::mapToCommandeResponse).collect(Collectors.toList());
    }
    @Transactional
    @Override
    public List<CommandeResponse> getBilanCommandesParSuperviseur(Long superviseurId) {
        List<Commande> commandes = commandeRepository.findBySuperviseurId(superviseurId);
        return commandes.stream().map(this::mapToCommandeResponse).collect(Collectors.toList());
    }


}