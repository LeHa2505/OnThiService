package cfm.onthi.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class QuizUserDTO {
    @JsonProperty(value = "ID_QUIZ_USER")
    public Long idQuizUser;

    @JsonProperty(value = "ID_USER")
    public Long idUser;

    @JsonProperty(value = "ID_QUIZ")
    public Long idQuiz;

    @JsonProperty(value = "USER_ANSWER")
    public String userAnswer;
}
