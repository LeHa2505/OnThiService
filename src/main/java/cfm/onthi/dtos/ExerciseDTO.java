package cfm.onthi.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ExerciseDTO {
    @JsonProperty(value = "ID_EXERCISE")
    public Long idExercise;

    @JsonProperty(value = "ID_LESSON")
    public Long idLesson;

    @JsonProperty(value = "EXERCISE_NAME")
    public String exerciseName;

    @JsonProperty(value = "QUIZ_INFO")
    public List<QuizDTO> quizInfo;
}
