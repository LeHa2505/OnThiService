package cfm.onthi.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

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
}
