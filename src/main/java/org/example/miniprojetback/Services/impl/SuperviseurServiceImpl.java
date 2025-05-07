package org.example.miniprojetback.Services.impl;

import jakarta.transaction.Transactional;
import org.example.miniprojetback.Models.Superviseur;
import org.example.miniprojetback.Repositories.SuperviseurRepository;
import org.example.miniprojetback.Services.ISuperviseurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SuperviseurServiceImpl implements ISuperviseurService {

    @Autowired
    private SuperviseurRepository superviseurRepository;

    @Override
    public Superviseur createSuperviseur(Superviseur superviseur) {
        return superviseurRepository.save(superviseur);
    }
    @Transactional
    @Override
    public Superviseur updateSuperviseur(Long id, Superviseur superviseur) {
        Optional<Superviseur> existingSuperviseur = superviseurRepository.findById(id);
        if (existingSuperviseur.isPresent()) {
            Superviseur updatedSuperviseur = existingSuperviseur.get();
            updatedSuperviseur.setName(superviseur.getName());  // Assurez-vous que vous définissez tous les champs nécessaires
            updatedSuperviseur.setEmail(superviseur.getEmail());
            // Ajoutez d'autres propriétés si nécessaire
            return superviseurRepository.save(updatedSuperviseur);
        }
        return null;  // Vous pouvez lancer une exception personnalisée si le superviseur n'existe pas
    }
    @Transactional
    @Override
    public Optional<Superviseur> getSuperviseurById(Long id) {
        return superviseurRepository.findById(id);
    }
    @Transactional
    @Override
    public List<Superviseur> getAllSuperviseurs() {
        return superviseurRepository.findAll();
    }
    @Transactional
    @Override
    public void deleteSuperviseur(Long id) {
        superviseurRepository.deleteById(id);
    }
}
