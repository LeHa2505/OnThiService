package cfm.onthi.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class NotificationDTO {
    @JsonProperty(value = "ID_NOTIFICATION")
    public Long idNotification;

    @JsonProperty(value = "ID_USER")
    public Long idUser;

    @JsonProperty(value = "CONTENT")
    public String content;

    @JsonProperty(value = "IS_SEEN")
    public Boolean isSeen;

    @JsonProperty(value = "TYPE_NOTIFICATION")
    public Long typeNotification;

    @JsonProperty(value = "CREATED_DATE")
    public LocalDateTime createdDate;
}
