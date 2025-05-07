package org.example.miniprojetback.DAOs.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ClientRequest extends  AuthRequest {
    private int points;
    private boolean isActive;

}
