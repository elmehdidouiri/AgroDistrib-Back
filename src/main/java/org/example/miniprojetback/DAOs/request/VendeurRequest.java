package org.example.miniprojetback.DAOs.request;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VendeurRequest {
    private String name;
    private String email;
    private String password;
}
