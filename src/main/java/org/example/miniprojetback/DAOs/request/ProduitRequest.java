package org.example.miniprojetback.DAOs.request;

import lombok.*;
import org.example.miniprojetback.Models.enums.FamilleProduit;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class ProduitRequest {

    private String nom;

    private double prix;

    private FamilleProduit familleProduit;

    private int quantite;

}
