package org.example.miniprojetback.Models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "vendeurs")
public class Vendeur extends User {
    // Pas de relation directe avec Commande, car les actions seront gérées via les services et contrôleurs
}