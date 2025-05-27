package org.example.miniprojetback.DAOs.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.miniprojetback.Models.enums.Role;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AuthResponse {

    private String email;
    private Role role;
    private String adresse;
    private Long id; // ID de l'utilisateur
    // Nouveau champ pour l'adresse

}


