package org.example.miniprojetback.Models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;


import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "commandes")
public class Commande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = true)
    private Client client;

    @Column(name = "date_commande", nullable = true)
    private Date dateCommande;

    @Column(name = "total", nullable = true)
    private Double total;

    @Column(name = "status", nullable = true)
    private String status;
    @Column(name = "price", nullable = true)
    private double TotalPrice;
    @ManyToOne
    @JoinColumn(name = "superviseur_id") // ou un autre nom si tu veux
    private Superviseur superviseur;
    @ManyToOne
    @JoinColumn(name = "vendeur_id") // Clé étrangère vers Vendeur
    private Vendeur vendeur;


}
