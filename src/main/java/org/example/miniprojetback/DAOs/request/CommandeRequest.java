package org.example.miniprojetback.DAOs.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.miniprojetback.Models.Client;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CommandeRequest {
    private Long client;
    private List<ProduitCommandeRequest> produits;
    private Long vendeurId;
    private Long superviseurId;
}