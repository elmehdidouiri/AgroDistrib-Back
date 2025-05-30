package org.example.miniprojetback.Services;

import org.example.miniprojetback.DAOs.response.CommandeResponse;
import org.example.miniprojetback.Models.Vendeur;

import java.util.List;
import java.util.Optional;

public interface IVendeurService {

    Vendeur createVendeur(Vendeur vendeur);

    Vendeur updateVendeur(Long id, Vendeur vendeur);

    Optional<Vendeur> getVendeurById(Long id);

    List<Vendeur> getAllVendeurs();

    void deleteVendeur(Long id);
    List<CommandeResponse> getCommandesParVendeur(Long vendeurId);
}
