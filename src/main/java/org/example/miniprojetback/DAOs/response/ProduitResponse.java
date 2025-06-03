package org.example.miniprojetback.DAOs.response;

import lombok.*;
import org.example.miniprojetback.Models.Produit;
import org.example.miniprojetback.Models.enums.FamilleProduit;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class ProduitResponse {
    private Long id;
    private String nom;
    private double prix;
    private FamilleProduit familleProduit;
    private int quantite;

    public ProduitResponse(Produit produit) {
        this.id = produit.getId();
        this.nom = produit.getNom();
        this.prix = produit.getPrix();
        this.familleProduit = produit.getFamilleProduit();
        this.quantite = produit.getQuantite();
    }
}