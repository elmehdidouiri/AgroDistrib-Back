package org.example.miniprojetback.Models;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "vendeurs")
public class Vendeur extends User {

    @OneToMany(mappedBy = "vendeur", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Commande> commandesLivres;

}
