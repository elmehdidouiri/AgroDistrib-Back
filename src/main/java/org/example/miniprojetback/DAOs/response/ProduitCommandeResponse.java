package org.example.miniprojetback.DAOs.response;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProduitCommandeResponse {
    private Long id;
    private Long produitId;
    private Integer quantity;
}
