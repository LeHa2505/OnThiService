package cfm.onthi.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserRoleDTO {
    @JsonProperty(value = "ID_USER_ROLE")
    public Long idUserRole;

    @JsonProperty(value = "ID_USER")
    public Long idUser;

    @JsonProperty(value = "ID_ROLE")
    public Long idRole;
}
