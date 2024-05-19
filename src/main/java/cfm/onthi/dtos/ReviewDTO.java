package cfm.onthi.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDTO {
    @JsonProperty(value = "ID_REVIEW")
    public Long idReview;

    @JsonProperty(value = "ID_COURSE")
    public Long idCourse;

    @JsonProperty(value = "ID_USER")
    public Long idUser;

    @JsonProperty(value = "CONTENT")
    public String content;
}
