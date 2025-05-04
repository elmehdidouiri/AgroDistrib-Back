package org.example.miniprojetback.DAOs.response;




import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VendeurResponse {
    private Long id;
    private String name;
    private String email;
}
