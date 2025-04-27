package org.example.miniprojetback.Controllers;

import org.example.miniprojetback.Dtos.request.AuthRequest;
import org.example.miniprojetback.Dtos.response.AuthResponse;
import org.example.miniprojetback.Services.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private IAuthService authService;

    // Endpoint pour se connecter
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest) {
        try {
            AuthResponse authResponse = authService.login(authRequest);
            return ResponseEntity.ok(authResponse);  // Retourne une réponse 200 avec les informations de l'utilisateur
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new AuthResponse("Invalid credentials",null));  // Si erreur, retourne une réponse 401 avec un message d'erreur
        }
    }

    // Endpoint pour s'inscrire
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody AuthRequest authRequest) {
        try {
            authService.register(authRequest);  // Appel à la méthode de service pour l'inscription
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("User registered successfully");  // Si l'inscription réussit, retourne une réponse 201
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error during registration: " + e.getMessage());  // En cas d'erreur, retourne une réponse 400
        }
    }
}
