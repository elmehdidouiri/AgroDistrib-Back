package org.example.miniprojetback.DAOs.request;




import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CommandeRequest {
    private Long clientId;
    private List<ProduitCommandeRequest> produitsCommande;
    private String status;
    private double TotalPrice;
}
