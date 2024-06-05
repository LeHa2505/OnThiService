package cfm.onthi.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GroupDTO {
    @JsonProperty(value = "ID_GROUP")
    public Long idGroup;

    @JsonProperty(value = "GROUP_NAME")
    public String groupName;

    @JsonProperty(value = "AVATAR_GROUP")
    public String avatarGroup;
}
