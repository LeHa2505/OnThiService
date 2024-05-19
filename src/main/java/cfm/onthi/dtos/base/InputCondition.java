package cfm.onthi.dtos.base;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InputCondition {
    @JsonProperty(value = "USERNAME")
    public String USERNAME;

    @JsonProperty(value = "EMAIL")
    public String EMAIL;

    @JsonProperty(value = "PASSWORD")
    public String PASSWORD;

    @JsonProperty(value = "ID_SCHOOL")
    public Long ID_SCHOOL;

    @JsonProperty(value = "ID_PROVINCE")
    public Long ID_PROVINCE;

    @JsonProperty(value = "GRADE")
    public Integer GRADE;

    @JsonProperty(value = "COURSE_NAME")
    public String COURSE_NAME;

    @JsonProperty(value = "CATEGORY_NAME")
    public String CATEGORY_NAME;

    @JsonProperty(value = "TYPE_COURSE")
    public Long TYPE_COURSE;

    @JsonProperty(value = "ID_COURSE")
    public Long ID_COURSE;

    @JsonProperty(value = "ID_GROUP")
    public Long ID_GROUP;

    @JsonProperty(value = "ID_DOCUMENT")
    public Long ID_DOCUMENT;

    @JsonProperty(value = "ID_LESSON")
    public Long ID_LESSON;
}
