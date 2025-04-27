package org.example.miniprojetback.Controllers;

import org.example.miniprojetback.Models.Superviseur;
import org.example.miniprojetback.Services.ISuperviseurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/superviseurs")
public class SuperviseurController {

    @Autowired
    private ISuperviseurService superviseurService;

    @PostMapping
    public ResponseEntity<Superviseur> createSuperviseur(@RequestBody Superviseur superviseur) {
        Superviseur createdSuperviseur = superviseurService.createSuperviseur(superviseur);
        return ResponseEntity.ok(createdSuperviseur);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Superviseur> updateSuperviseur(@PathVariable Long id, @RequestBody Superviseur superviseur) {
        Superviseur updatedSuperviseur = superviseurService.updateSuperviseur(id, superviseur);
        if (updatedSuperviseur != null) {
            return ResponseEntity.ok(updatedSuperviseur);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Superviseur> getSuperviseurById(@PathVariable Long id) {
        Optional<Superviseur> superviseur = superviseurService.getSuperviseurById(id);
        return superviseur.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Superviseur>> getAllSuperviseurs() {
        List<Superviseur> superviseurs = superviseurService.getAllSuperviseurs();
        return ResponseEntity.ok(superviseurs);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSuperviseur(@PathVariable Long id) {
        superviseurService.deleteSuperviseur(id);
        return ResponseEntity.noContent().build();
    }
}
