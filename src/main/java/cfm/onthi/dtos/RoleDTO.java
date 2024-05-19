package cfm.onthi.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RoleDTO {
    @JsonProperty(value = "ID_ROLE")
    public Long idRole;

    @JsonProperty(value = "ROLE_DESCRIBE")
    public String roleDescribe;

    @JsonProperty(value = "ROLE_KEY")
    public String roleKey;

    @JsonProperty(value = "ROLE_NAME")
    public String roleName;

    @JsonProperty(value = "ACTIVE")
    public Boolean active;
}
