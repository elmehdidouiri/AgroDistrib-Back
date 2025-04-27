package org.example.miniprojetback.Services;



import org.example.miniprojetback.Dtos.request.ProduitCommandeRequest;
import org.example.miniprojetback.Models.ProduitCommande;

import java.util.List;

public interface IProduitCommandeService {
    ProduitCommande createProduitCommande(ProduitCommandeRequest produitCommandeRequest);
    ProduitCommande updateProduitCommande(Long produitCommandeId, ProduitCommandeRequest produitCommandeRequest);
    void deleteProduitCommande(Long produitCommandeId);
    ProduitCommande getProduitCommandeById(Long produitCommandeId);
    List<ProduitCommande> getAllProduitsCommandes();
}
