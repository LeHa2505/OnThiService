package cfm.onthi.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class NoteDTO {
    @JsonProperty(value = "ID_NOTE")
    public Long idNote;

    @JsonProperty(value = "ID_USER")
    public Long idUser;

    @JsonProperty(value = "ID_LESSON")
    public Long idLesson;

    @JsonProperty(value = "CONTENT")
    public String content;

    @JsonProperty(value = "NOTE_TIME")
    public Double noteTime;
}
