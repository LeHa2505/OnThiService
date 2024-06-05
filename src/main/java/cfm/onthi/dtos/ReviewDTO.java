package cfm.onthi.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

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

    @JsonProperty(value = "ID_LESSON")
    public Long idLesson;

    @JsonProperty(value = "ID_USER")
    public Long idUser;

    @JsonProperty(value = "CONTENT")
    public String content;

    @JsonProperty(value = "RATING")
    public Integer rating;

    @JsonProperty(value = "LIKE")
    public Integer like;

    @JsonProperty(value = "DISLIKE")
    public Integer dislike;

    @JsonProperty(value = "USER_INTERACT_LIST")
    public List<ReviewUserDTO> userInteractList;

    @JsonProperty(value = "ID_USER_INTERACT")

    public Long IdUserInteract;

    @JsonProperty(value = "USER_INTERACT_ACTION")
    public Long userInteractAction;

    @JsonProperty(value = "USER_INFO")
    public UserInfoDTO userInfo;

    @JsonProperty(value = "CREATED_DATE")
    public LocalDateTime createdDate;
}
