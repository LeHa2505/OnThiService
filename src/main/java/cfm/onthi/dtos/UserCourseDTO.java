package cfm.onthi.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserCourseDTO {
    @JsonProperty(value = "ID_USER_COURSE")
    public Long idUserCourse;

    @JsonProperty(value = "ID_USER")
    public Long idUser;

    @JsonProperty(value = "ID_COURSE")
    public Long idCourse;

    @JsonProperty(value = "COURSE_INFO")
    public CourseDTO courseInfo;

    @JsonProperty(value = "CLASSMATES")
    public List<UserInfoDTO> classmates;

    @JsonProperty(value = "TIME_SCHEDULE")
    public String timeSchedule;

    @JsonProperty(value = "LEARNED_LESSON")
    public String learnedLesson;

    @JsonProperty(value = "LEARNING_LESSON")
    public Long learningLesson;
}
