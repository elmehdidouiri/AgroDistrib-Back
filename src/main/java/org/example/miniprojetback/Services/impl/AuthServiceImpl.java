package org.example.miniprojetback.Services.impl;

import org.example.miniprojetback.DAOs.request.AuthRequest;
import org.example.miniprojetback.DAOs.response.AuthResponse;
import org.example.miniprojetback.Exceptions.InvalidCredentialsException;
import org.example.miniprojetback.Models.Client;
import org.example.miniprojetback.Models.Superviseur;
import org.example.miniprojetback.Models.User;
import org.example.miniprojetback.Models.Vendeur;
import org.example.miniprojetback.Models.enums.Role;
import org.example.miniprojetback.Repositories.UserRepository;
import org.example.miniprojetback.Services.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuthServiceImpl implements IAuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;  // Injection de BCryptPasswordEncoder

    @Override
    public AuthResponse login(AuthRequest authRequest) {
        try {
            // Recherche de l'utilisateur dans la base de données par email
            User user = userRepository.findByEmail(authRequest.getEmail())
                    .orElseThrow(() -> new InvalidCredentialsException("Invalid email or password"));
            // Vérification du mot de passe (comparaison entre le mot de passe encodé et celui fourni)
            if (!passwordEncoder.matches(authRequest.getPassword(), user.getPassword())) {
                throw new InvalidCredentialsException("Invalid email or password");
            }
            AuthResponse response = new AuthResponse();
            response.setEmail(user.getEmail());  // Set email de l'utilisateur
            response.setRole(Role.valueOf(user.getRole().name()));  // Set le rôle de l'utilisateur (converti en string)

            return response;

        } catch (Exception e) {
            // Log l'exception pour le débogage
            e.printStackTrace();
            throw new InvalidCredentialsException("An error occurred while logging in");
        }
    }

    @Override
    public void register(AuthRequest authRequest) {
        try {
            // Vérification si l'email est déjà utilisé
            userRepository.findByEmail(authRequest.getEmail()).ifPresent(user -> {
                throw new InvalidCredentialsException("Email already in use");
            });

            // Création d'un utilisateur de base
            User user;
            Role role = Role.valueOf(String.valueOf(authRequest.getRole())); // conversion String -> Enum
            switch (role) {
                case CLIENT -> {
                    user = new Client(); // Spécifique au rôle CLIENT
                }
                case SUPERVISEUR -> {
                    user = new Superviseur(); // Spécifique au rôle SUPERVISEUR
                }
                case VENDEUR -> {
                    user = new Vendeur(); // Spécifique au rôle VENDEUR
                }
                default -> throw new IllegalArgumentException("Unknown role: " + role);
            }

            // Assignation des attributs communs
            user.setEmail(authRequest.getEmail());
            user.setPassword(passwordEncoder.encode(authRequest.getPassword()));
            user.setRole(role);
            user.setAdresse(authRequest.getAdresse());
            user.setTelephone(authRequest.getTelephone());
            user.setName(authRequest.getName());
            user.setCreatedAt(LocalDateTime.now());

            userRepository.save(user); // Sauvegarde de l'utilisateur dans la table `users`

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("An error occurred while registering the user");
        }
}
}
