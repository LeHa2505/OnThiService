package cfm.onthi.dtos;

import cfm.onthi.constant.MessageType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MessageDTO {
    @JsonProperty(value = "ID_MESSAGE")
    public Long idMessage;

    @JsonProperty(value = "ID_CREATOR")
    public Long idCreator;

    @JsonProperty(value = "MESSAGE_BODY")
    public String messageBody;

    @JsonProperty(value = "USERNAME")
    public String username;

    @JsonProperty(value = "ID_GROUP")
    public Long idGroup;

    @JsonProperty(value = "AVATAR")
    public String avatar;
}
