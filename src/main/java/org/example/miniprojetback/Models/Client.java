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
@Table(name = "clients")
public class Client extends User {

    @Column(name = "points", nullable = true)
    private int points;

    @Column(name = "is_active", nullable = true)
    private boolean isActive;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Commande> commandes;

}