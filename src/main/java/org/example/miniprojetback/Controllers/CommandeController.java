package org.example.miniprojetback.Controllers;

import org.example.miniprojetback.DAOs.request.CommandeRequest;
import org.example.miniprojetback.DAOs.response.CommandeResponse;
import org.example.miniprojetback.Services.ICommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/commandes")
public class CommandeController {

    @Autowired
    private ICommandeService commandeService;

    @PostMapping
    public ResponseEntity<CommandeResponse> createCommande(@RequestBody CommandeRequest request) {
        CommandeResponse response = commandeService.createCommande(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{commandeId}/valider-paiement")
    public ResponseEntity<CommandeResponse> validerPaiement(@PathVariable Long commandeId) {
        CommandeResponse response = commandeService.validerPaiement(commandeId);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{commandeId}/modifier-statut")
    public ResponseEntity<CommandeResponse> modifierStatutCommande(
            @PathVariable Long commandeId,
            @RequestParam String nouveauStatut) {
        CommandeResponse response = commandeService.modifierStatutCommande(commandeId, nouveauStatut);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/superviseur/{superviseurId}")
    public ResponseEntity<List<CommandeResponse>> getCommandesParSuperviseur(@PathVariable Long superviseurId) {
        List<CommandeResponse> responses = commandeService.getCommandesParSuperviseur(superviseurId);
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<CommandeResponse>> getCommandesParClient(@PathVariable Long clientId) {
        List<CommandeResponse> responses = commandeService.getCommandesParClient(clientId);
        return ResponseEntity.ok(responses);
    }

    @GetMapping
    public ResponseEntity<List<CommandeResponse>> getToutesLesCommandes() {
        List<CommandeResponse> responses = commandeService.getToutesLesCommandes();
        return ResponseEntity.ok(responses);
    }
}