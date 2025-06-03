package org.example.miniprojetback.Services.impl;

import jakarta.transaction.Transactional;
import org.example.miniprojetback.DAOs.request.ProduitCommandeRequest;
import org.example.miniprojetback.DAOs.response.CommandeResponse;
import org.example.miniprojetback.Exceptions.ResourceNotFoundException;
import org.example.miniprojetback.Models.Commande;
import org.example.miniprojetback.Models.Vendeur;
import org.example.miniprojetback.Repositories.CommandeRepository;
import org.example.miniprojetback.Repositories.VendeurRepository;
import org.example.miniprojetback.Services.IVendeurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VendeurServiceImpl implements IVendeurService {

    private final VendeurRepository vendeurRepository;
    private final CommandeRepository commandeRepository;

    @Autowired
    public VendeurServiceImpl(VendeurRepository vendeurRepository,CommandeRepository commandeRepository) {
        this.vendeurRepository = vendeurRepository;
        this.commandeRepository = commandeRepository;
    }

    @Transactional
    @Override
    public Vendeur createVendeur(Vendeur vendeur) {
        // Sauvegarde le vendeur dans la base de données
        return vendeurRepository.save(vendeur);
    }
    @Transactional
    @Override
    public Vendeur updateVendeur(Long id, Vendeur vendeur) {
        // Recherche du vendeur par ID
        Optional<Vendeur> existingVendeur = vendeurRepository.findById(id);

        if (existingVendeur.isPresent()) {
            Vendeur currentVendeur = existingVendeur.get();
            // Met à jour les informations du vendeur
            currentVendeur.setEmail(vendeur.getEmail());
            currentVendeur.setPassword(vendeur.getPassword());
            currentVendeur.setRole(vendeur.getRole());
            // Sauvegarde les modifications
            return vendeurRepository.save(currentVendeur);
        } else {
            throw new RuntimeException("Vendeur not found with id: " + id);
        }
    }
    @Transactional
    @Override
    public Optional<Vendeur> getVendeurById(Long id) {
        // Récupère le vendeur par son ID
        return vendeurRepository.findById(id);
    }
    @Transactional
    @Override
    public List<Vendeur> getAllVendeurs() {
        // Récupère tous les vendeurs
        return vendeurRepository.findAll();
    }
    @Transactional
    @Override
    public void deleteVendeur(Long id) {
        // Supprime le vendeur par son ID
        vendeurRepository.deleteById(id);
    }
    @Override
    public List<CommandeResponse> getCommandesParVendeur(Long vendeurId) {
        Vendeur vendeur = vendeurRepository.findById(vendeurId)
                .orElseThrow(() -> new ResourceNotFoundException("Vendeur non trouvé avec l'ID : " + vendeurId));

        List<Commande> commandes = commandeRepository.findByVendeur(vendeur);

        return commandes.stream()
                .map(this::mapToCommandeResponse)
                .collect(Collectors.toList());
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
                            return produitRequest;
                        })
                        .collect(Collectors.toList())
        );
        response.setClientId(commande.getClient().getId());
        response.setClientNom(commande.getClient().getName());
        response.setClientAdresse(commande.getClient().getAdresse()); // Récupération de l'adresse du client
        return response;
    }
}
