package org.example.miniprojetback.DAOs.request;




import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProduitCommandeRequest {
    private Long produitId;
    private Integer quantity;
}
