package org.example.miniprojetback.DAOs.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ClientRequest {
    private String name;
    private String telephone;
    private String adresse;
    private String email;
    private String password;
    private int points;
    private boolean isActive;

}
