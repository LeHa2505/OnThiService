package cfm.onthi.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

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

    @JsonProperty(value = "CATEGORY_NAME")
    public String category_name;

    @JsonProperty(value = "SCHEDULE")
    public Double schedule;

    @JsonProperty(value = "COURSE_NAME")
    public String course_name;

    @JsonProperty(value = "TYPE_COURSE")
    public Long type_course;

    @JsonProperty(value = "START_DATE")
    public LocalDate start_date;

    @JsonProperty(value = "END_DATE")
    public LocalDate end_date;

    @JsonProperty(value = "DURATION")
    public Double duration;

    @JsonProperty(value = "PRICE")
    public Double price;

    @JsonProperty(value = "DISCOUNT")
    public Double discount;

    @JsonProperty(value = "DESCRIPTION")
    public String description;
}
