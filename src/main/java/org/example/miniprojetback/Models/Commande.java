package org.example.miniprojetback.Models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.miniprojetback.Models.enums.CommandeStatus;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "commandes")
public class Commande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @Column(name = "date_commande", nullable = false)
    private LocalDateTime dateCommande;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private CommandeStatus status;

    @Column(name = "paiement_valide", nullable = false)
    private boolean paiementValide = false;

    @OneToMany(mappedBy = "commande", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LigneCommande> lignesCommande;

    @ManyToOne
    @JoinColumn(name = "superviseur_id")
    private Superviseur superviseur;

    @ManyToOne
    @JoinColumn(name = "vendeur_id")
    private Vendeur vendeur;
}