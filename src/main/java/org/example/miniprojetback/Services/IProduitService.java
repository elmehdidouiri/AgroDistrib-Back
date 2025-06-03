package org.example.miniprojetback.Services;


import org.example.miniprojetback.DAOs.request.ProduitRequest;
import org.example.miniprojetback.DAOs.response.ProduitResponse;

import java.util.List;

public interface IProduitService {
    ProduitResponse createProduit(ProduitRequest request);
    List<ProduitResponse> getAllProduits();
    ProduitResponse getProduitById(Long id);
    ProduitResponse updateProduitPartiel(Long id, ProduitRequest request);
    void deleteProduit(Long id);
}