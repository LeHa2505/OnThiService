package cfm.onthi.dtos;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class RegisterDTO {
    private String email;
    private String username;
    private String password;
    private Long typeUser;
}
