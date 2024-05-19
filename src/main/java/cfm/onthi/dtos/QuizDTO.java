package cfm.onthi.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class QuizDTO {
    @JsonProperty(value = "ID_QUIZ")
    public Long idQuiz;

    @JsonProperty(value = "ID_LESSON")
    public Long idLesson;

    @JsonProperty(value = "CONTENT_QUIZ")
    public String contentQuiz;
}
