package org.example.miniprojetback.Services.impl;

import jakarta.transaction.Transactional;
import org.example.miniprojetback.DAOs.request.ProduitRequest;
import org.example.miniprojetback.DAOs.response.ProduitResponse;
import org.example.miniprojetback.Exceptions.ResourceNotFoundException;
import org.example.miniprojetback.Models.Produit;
import org.example.miniprojetback.Repositories.ProduitRepository;
import org.example.miniprojetback.Services.IProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProduitServiceImpl implements IProduitService {

    @Autowired
    private ProduitRepository produitRepository;

    @Transactional
    @Override
    public ProduitResponse createProduit(ProduitRequest request) {
        Produit produit = new Produit();
        produit.setNom(request.getNom());
        produit.setPrix(request.getPrix());
        produit.setFamilleProduit(request.getFamilleProduit());
        produit.setQuantite(request.getQuantite());
        produit = produitRepository.save(produit);
        return mapToProduitResponse(produit);
    }

    @Transactional
    @Override
    public ProduitResponse updateProduitPartiel(Long id, ProduitRequest request) {
        Produit produit = produitRepository.findById(id).orElse(null);
        if (produit == null) {
            return null;
        }

        if (request.getNom() != null) {
            produit.setNom(request.getNom());
        }
        if (request.getPrix() != 0) {
            produit.setPrix(request.getPrix());
        }
        if (request.getFamilleProduit() != null) {
            produit.setFamilleProduit(request.getFamilleProduit());
        }
        if (request.getQuantite() != 0) {
            produit.setQuantite(request.getQuantite());
        }

        produitRepository.save(produit);
        return new ProduitResponse(produit);
    }

    @Transactional
    @Override
    public List<ProduitResponse> getAllProduits() {
        return produitRepository.findAll().stream()
                .map(this::mapToProduitResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public ProduitResponse getProduitById(Long id) {
        Produit produit = produitRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produit non trouvé avec l'ID : " + id));
        return mapToProduitResponse(produit);
    }

    @Transactional
    @Override
    public void deleteProduit(Long id) {
        Produit produit = produitRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produit non trouvé avec l'ID : " + id));
        produitRepository.delete(produit);
    }

    private ProduitResponse mapToProduitResponse(Produit produit) {
        ProduitResponse response = new ProduitResponse();
        response.setId(produit.getId());
        response.setNom(produit.getNom());
        response.setPrix(produit.getPrix());
        response.setFamilleProduit(produit.getFamilleProduit());
        response.setQuantite(produit.getQuantite());
        return response;
    }
}