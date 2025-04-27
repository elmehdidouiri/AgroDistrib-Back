package org.example.miniprojetback.Models;



import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Data
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "superviseurs")
public class Superviseur extends User {

    @OneToMany(mappedBy = "superviseur", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Commande> commandes;


}
