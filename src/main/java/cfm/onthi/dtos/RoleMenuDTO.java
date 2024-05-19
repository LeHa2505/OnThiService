package cfm.onthi.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RoleMenuDTO {
    @JsonProperty(value = "ID_ROLE_MENU")
    public Long idRoleMenu;

    @JsonProperty(value = "ID_ROLE")
    public Long idRole;

    @JsonProperty(value = "ID_MENU")
    public Long idMenu;
}
