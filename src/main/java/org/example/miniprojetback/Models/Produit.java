package org.example.miniprojetback.Models;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.miniprojetback.Models.enums.FamilleProduit;


@Data
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "produits")
public class Produit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nom", nullable = false)
    private String nom;

    @Column(name = "prix", nullable = false)
    private double prix;

    @Enumerated(EnumType.STRING)
    @Column(name = "famille_produit", nullable = false)
    private FamilleProduit familleProduit;

    @Column(name = "quantite", nullable = false)
    private int quantite;

}
