package org.example.miniprojetback.Exceptions;


public class InvalidCredentialsException extends RuntimeException {

    // Constructeur sans argument
    public InvalidCredentialsException() {
        super("Invalid credentials provided");
    }

    // Constructeur avec message personnalisé
    public InvalidCredentialsException(String message) {
        super(message); // On passe le message au constructeur parent (RuntimeException)
    }
}
