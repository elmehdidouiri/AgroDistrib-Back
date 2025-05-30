package org.example.miniprojetback.Controllers;

import org.example.miniprojetback.DAOs.response.CommandeResponse;
import org.example.miniprojetback.Models.Vendeur;
import org.example.miniprojetback.Services.IVendeurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/vendeurs")
public class VendeurController {

    private final IVendeurService vendeurService;

    @Autowired
    public VendeurController(IVendeurService vendeurService) {
        this.vendeurService = vendeurService;
    }

    @PostMapping
    public Vendeur createVendeur(@RequestBody Vendeur vendeur) {
        return vendeurService.createVendeur(vendeur);
    }

    @PutMapping("/{id}")
    public Vendeur updateVendeur(@PathVariable Long id, @RequestBody Vendeur vendeur) {
        return vendeurService.updateVendeur(id, vendeur);
    }

    @GetMapping("/{id}")
    public Optional<Vendeur> getVendeurById(@PathVariable Long id) {
        return vendeurService.getVendeurById(id);
    }

    @GetMapping
    public List<Vendeur> getAllVendeurs() {
        return vendeurService.getAllVendeurs();
    }

    @DeleteMapping("/{id}")
    public void deleteVendeur(@PathVariable Long id) {
        vendeurService.deleteVendeur(id);
    }
    @GetMapping("/{vendeurId}/commandes")
    public ResponseEntity<List<CommandeResponse>> getCommandesParVendeur(@PathVariable Long vendeurId) {
        List<CommandeResponse> commandes = vendeurService.getCommandesParVendeur(vendeurId);
        return ResponseEntity.ok(commandes);
    }
}
