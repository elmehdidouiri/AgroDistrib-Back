package org.example.miniprojetback.Services;

import org.example.miniprojetback.DAOs.request.CommandeRequest;
import org.example.miniprojetback.DAOs.response.CommandeResponse;

import java.util.List;

public interface ICommandeService {
    CommandeResponse createCommande(CommandeRequest request);

    CommandeResponse validerPaiement(Long commandeId);

    CommandeResponse modifierStatutCommande(Long commandeId, String nouveauStatut);

    List<CommandeResponse> getCommandesParSuperviseur(Long superviseurId);

    List<CommandeResponse> getCommandesParClient(Long clientId);

    List<CommandeResponse> getToutesLesCommandes();
}