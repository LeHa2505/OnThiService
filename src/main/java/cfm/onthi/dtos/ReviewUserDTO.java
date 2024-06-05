package cfm.onthi.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewUserDTO {
    @JsonProperty(value = "ID_REVIEW_USER")
    public Long idReviewUser;

    @JsonProperty(value = "ID_REVIEW")
    public Long idReview;

    @JsonProperty(value = "ID_USER")
    public Long idUser;

    @JsonProperty(value = "ACTION")
    public Long action;
}
