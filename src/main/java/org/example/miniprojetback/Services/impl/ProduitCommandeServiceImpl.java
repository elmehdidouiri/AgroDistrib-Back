package org.example.miniprojetback.Services.impl;


import org.example.miniprojetback.Models.ProduitCommande;
import org.example.miniprojetback.Repositories.ProduitCommandeRepository;
import org.example.miniprojetback.Services.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProduitCommandeServiceImpl implements CrudService<ProduitCommande> {

    @Autowired
    private ProduitCommandeRepository produitCommandeRepository;

    @Override
    public ProduitCommande create(ProduitCommande produitCommande) {
        return produitCommandeRepository.save(produitCommande);
    }

    @Override
    public Optional<ProduitCommande> getById(Long id) {
        return produitCommandeRepository.findById(id);
    }

    @Override
    public List<ProduitCommande> getAll() {
        return produitCommandeRepository.findAll();
    }

    @Override
    public ProduitCommande update(Long id, ProduitCommande produitCommande) {
        if (produitCommandeRepository.existsById(id)) {
            produitCommande.setId(id);
            return produitCommandeRepository.save(produitCommande);
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        produitCommandeRepository.deleteById(id);
    }
}
