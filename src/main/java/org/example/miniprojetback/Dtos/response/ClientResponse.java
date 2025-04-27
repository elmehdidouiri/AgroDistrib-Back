package org.example.miniprojetback.Dtos.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ClientResponse {

    private Long id;
    private String name;
    private String telephone;
    private String adresse;
    private String email;
    private String password;
    private int points;
    private boolean isActive;

}
