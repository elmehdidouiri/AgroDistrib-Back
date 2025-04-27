package org.example.miniprojetback.Models;


import jakarta.persistence.*;


import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.miniprojetback.Models.enums.Role;


import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)  // Pour gérer l'héritage des rôles
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "FullName", nullable = true)
    private String name;

    @Column(name = "Telephone", nullable = true)
    private String Telephone;

    @Column(name = "Adresse", nullable = true)
    private String Adresse;


    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;

    @Column(name = "created_at", nullable = true)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

}
