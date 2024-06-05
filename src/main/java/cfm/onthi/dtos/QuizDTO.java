package cfm.onthi.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Lob;
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

    @JsonProperty(value = "QUIZ_TYPE")
    public Long quizType;

    @JsonProperty(value = "ANSWER")
    @Lob
    public String answer;

    @JsonProperty(value = "QUIZ_STATUS")
    public Long quizStatus;

    @JsonProperty(value = "ORDER")
    public Integer order;
}
