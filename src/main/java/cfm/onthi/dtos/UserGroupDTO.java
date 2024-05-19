package cfm.onthi.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserGroupDTO {
    @JsonProperty(value = "ID_USER_GROUP")
    public Long idUserGroup;

    @JsonProperty(value = "ID_USER")
    public Long idUser;

    @JsonProperty(value = "ID_GROUP")
    public Long idGroup;
}
