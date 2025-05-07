package org.example.miniprojetback.Controllers;

import jakarta.validation.Valid;
import org.example.miniprojetback.DAOs.request.ProduitRequest;
import org.example.miniprojetback.DAOs.response.ProduitResponse;
import org.example.miniprojetback.Services.IProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produits")
public class ProduitController {

    @Autowired
    private IProduitService produitService;

    @PostMapping
    public ResponseEntity<ProduitResponse> createProduit( @RequestBody ProduitRequest request) {
        try {
            ProduitResponse produit = produitService.createProduit(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(produit);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<ProduitResponse>> getAllProduits() {
        try {
            List<ProduitResponse> produits = produitService.getAllProduits();
            return ResponseEntity.ok(produits);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProduitResponse> getProduitById(@PathVariable Long id) {
        try {
            ProduitResponse produit = produitService.getProduitById(id);
            if (produit != null) {
                return ResponseEntity.ok(produit);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProduitResponse> updateProduit(@PathVariable Long id, @Valid @RequestBody ProduitRequest request) {
        try {
            ProduitResponse produit = produitService.updateProduit(id, request);
            if (produit != null) {
                return ResponseEntity.ok(produit);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduit(@PathVariable Long id) {
        try {
            produitService.deleteProduit(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}