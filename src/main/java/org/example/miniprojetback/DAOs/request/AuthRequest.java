package org.example.miniprojetback.DAOs.request;


import lombok.*;
import org.example.miniprojetback.Models.enums.Role;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class AuthRequest {

        private String email;
        private String password;
        private Role role;
        private String name;
        private String Telephone;
        private String Adresse;
}
