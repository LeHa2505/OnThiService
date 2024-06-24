package cfm.onthi.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CourseDTO {
    @JsonProperty(value = "ID_COURSE")
    public Long id_course;

    @JsonProperty(value = "ID_TEACHER")
    public Long id_teacher;

    @JsonProperty(value = "AVATAR_COURSE")
    public String avatar_course;

    @JsonProperty(value = "ACTIVE")
    public Boolean active;

    @JsonProperty(value = "IS_CHECK")
    public Boolean isCheck;

    @JsonProperty(value = "CATEGORY_NAME")
    public String category_name;

    @JsonProperty(value = "IS_SUBMITTED")
    public Boolean isSubmitted ;

    @JsonProperty(value = "COURSE_NAME")
    public String course_name;

    @JsonProperty(value = "TYPE_COURSE")
    public Long type_course;

    @JsonProperty(value = "START_DATE")
    public LocalDate start_date;

    @JsonProperty(value = "END_DATE")
    public LocalDate end_date;

    @JsonProperty(value = "PRICE")
    public Double price;

    @JsonProperty(value = "DISCOUNT")
    public Double discount;

    @JsonProperty(value = "DESCRIPTION")
    public String description;

    @JsonProperty(value = "TEACHER_INFO")
    public UserInfoDTO teacher_info;

    @JsonProperty(value = "QUIZ_QUANTITY")
    public Integer quiz_quantity;

    @JsonProperty(value = "LESSON_QUANTITY")
    public Integer lesson_quantity;

    @JsonProperty(value = "LESSON_INFO")
    public List<LessonDTO> lesson_info;

    @JsonProperty(value = "REVIEW")
    public List<ReviewDTO> review;
}
