package org.example.miniprojetback.Services;

import org.example.miniprojetback.Models.Superviseur;

import java.util.List;
import java.util.Optional;

public interface ISuperviseurService {

    Superviseur createSuperviseur(Superviseur superviseur);

    Superviseur updateSuperviseur(Long id, Superviseur superviseur);

    Optional<Superviseur> getSuperviseurById(Long id);

    List<Superviseur> getAllSuperviseurs();

    void deleteSuperviseur(Long id);
}
