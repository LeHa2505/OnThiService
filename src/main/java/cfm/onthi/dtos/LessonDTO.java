package cfm.onthi.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LessonDTO {
    @JsonProperty(value = "ID_LESSON")
    public Long idLesson;

    @JsonProperty(value = "ID_COURSE")
    public Long idCourse;

    @JsonProperty(value = "LESSON_PARENT")
    public Long lessonParent;

    @JsonProperty(value = "LESSON_NAME")
    public String lessonName;

    @JsonProperty(value = "LINK_VIDEO")
    public String linkVideo;

    @JsonProperty(value = "DURATION")
    public Double duration;

    @JsonProperty(value = "SUBJECT")
    public String subject;

    @JsonProperty(value = "ORDER")
    public Long order;

    @JsonProperty(value = "CONTINUE_TIME")
    public Double continueTime;

    @JsonProperty(value = "VIEW")
    public Long view;

    @JsonProperty(value = "DESCRIPTION")
    public String description;

    @JsonProperty(value = "EXERCISE_INFO")
    public List<ExerciseDTO> exerciseInfo;

    @JsonProperty(value = "DOCUMENTS_INFO")
    public List<DocumentDTO> documentsInfo;
}
