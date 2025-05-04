package org.example.miniprojetback.DAOs.response;




import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.miniprojetback.DAOs.request.ProduitCommandeRequest;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CommandeResponse {
    private Long id;
    private Long clientId;
    private List<ProduitCommandeRequest> produitsCommande;
    private String status;
    private Double totalAmount;
}
