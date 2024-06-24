package cfm.onthi.dtos.base;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InputCondition {
    @JsonProperty(value = "USERNAME")
    public String USERNAME;

    @JsonProperty(value = "ID_USER")
    public Long ID_USER;

    @JsonProperty(value = "EMAIL")
    public String EMAIL;

    @JsonProperty(value = "PASSWORD")
    public String PASSWORD;

    @JsonProperty(value = "ID_SCHOOL")
    public Long ID_SCHOOL;

    @JsonProperty(value = "ID_PROVINCE")
    public Long ID_PROVINCE;

    @JsonProperty(value = "PROVINCE_NAME")
    public String PROVINCE_NAME;

    @JsonProperty(value = "GRADE")
    public Integer GRADE;

    @JsonProperty(value = "COURSE_NAME")
    public String COURSE_NAME;

    @JsonProperty(value = "CATEGORY_NAME")
    public String CATEGORY_NAME;

    @JsonProperty(value = "LIST_CATEGORY_NAME")
    public List<String> LIST_CATEGORY_NAME;

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

    @JsonProperty(value = "ID_REVIEW")
    public Long ID_REVIEW;

    @JsonProperty(value = "ID_NOTE")
    public Long ID_NOTE;

    @JsonProperty(value = "ID_QUIZ")
    public Long ID_QUIZ;

    @JsonProperty(value = "ID_EXERCISE")
    public Long ID_EXERCISE;

    @JsonProperty(value = "ID_USER_COURSE")
    public Long ID_USER_COURSE;

    @JsonProperty(value = "ACTIVE")
    public Boolean ACTIVE;

    @JsonProperty(value = "IS_CHECK")
    public Boolean IS_CHECK;

    @JsonProperty(value = "ID_SHORT")
    public Long ID_SHORT;
}
