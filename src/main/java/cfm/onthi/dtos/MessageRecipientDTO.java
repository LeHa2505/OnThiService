package cfm.onthi.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MessageRecipientDTO {
    @JsonProperty(value = "ID_MESSAGE_RECIPIENT")
    public Long idMessageRecipient;
    
    @JsonProperty(value = "ID_RECIPIENT_GROUP")
    public Long idRecipientGroup;
    
    @JsonProperty(value = "ID_MESSAGE")
    public Long idMessage;
}
