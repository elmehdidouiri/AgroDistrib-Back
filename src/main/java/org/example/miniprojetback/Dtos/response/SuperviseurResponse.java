package org.example.miniprojetback.Dtos.response;




import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SuperviseurResponse {
    private Long id;
    private String name;
    private String email;
}

