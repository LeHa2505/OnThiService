package cfm.onthi.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Lob;
import lombok.*;

import java.util.List;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class QuizDTO {
    @JsonProperty(value = "ID_QUIZ")
    public Long idQuiz;

    @JsonProperty(value = "ID_EXERCISE")
    public Long idExercise;

    @JsonProperty(value = "CONTENT_QUIZ")
    @Lob
    public String contentQuiz;

    @JsonProperty(value = "OPTIONS")
    public String options;

    @JsonProperty(value = "QUIZ_TYPE")
    public Long quizType;

    @JsonProperty(value = "ANSWER")
    @Lob
    public String answer;

    @JsonProperty(value = "DESCRIPTION")
    @Lob
    public String description;

    @JsonProperty(value = "QUIZ_STATUS")
    public Long quizStatus;

    @JsonProperty(value = "ORDER")
    public Integer order;

    @JsonProperty(value = "QUIZ_USER_INFO")
    public List<QuizUserDTO> quizUserInfo;
}
