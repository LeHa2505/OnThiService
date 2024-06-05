package cfm.onthi.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DocumentDTO {
    @JsonProperty(value = "ID_DOCUMENT")
    public Long idDocument;

    @JsonProperty(value = "ID_LESSON")
    public Long idLesson;

    @JsonProperty(value = "DOCUMENT_LINK")
    public String documentLink;

    @JsonProperty(value = "DOCUMENT_NAME")
    public String documentName;

    @JsonProperty(value = "TYPE_DOCUMENT")
    public String typeDocument;

    @JsonProperty(value = "DOWNLOAD_STATUS")
    public Long downloadStatus;

    @JsonProperty(value = "SIZE")
    public Double size;
}
