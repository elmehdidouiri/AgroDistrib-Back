package org.example.miniprojetback.DAOs.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.miniprojetback.DAOs.request.ProduitCommandeRequest;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CommandeResponse {
    private Long id;
    private LocalDateTime dateCommande;
    private String status;
    private List<ProduitCommandeRequest> produits;
    private String clientNom;
    private long clientId;
    private String clientAdresse;
}