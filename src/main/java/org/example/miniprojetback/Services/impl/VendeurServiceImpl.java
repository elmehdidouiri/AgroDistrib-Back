package org.example.miniprojetback.Services.impl;

import org.example.miniprojetback.Models.Vendeur;
import org.example.miniprojetback.Repositories.VendeurRepository;
import org.example.miniprojetback.Services.IVendeurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VendeurServiceImpl implements IVendeurService {

    private final VendeurRepository vendeurRepository;

    @Autowired
    public VendeurServiceImpl(VendeurRepository vendeurRepository) {
        this.vendeurRepository = vendeurRepository;
    }

    @Override
    public Vendeur createVendeur(Vendeur vendeur) {
        // Sauvegarde le vendeur dans la base de données
        return vendeurRepository.save(vendeur);
    }

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

    @Override
    public Optional<Vendeur> getVendeurById(Long id) {
        // Récupère le vendeur par son ID
        return vendeurRepository.findById(id);
    }

    @Override
    public List<Vendeur> getAllVendeurs() {
        // Récupère tous les vendeurs
        return vendeurRepository.findAll();
    }

    @Override
    public void deleteVendeur(Long id) {
        // Supprime le vendeur par son ID
        vendeurRepository.deleteById(id);
    }
}
