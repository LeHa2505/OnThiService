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
public class UserInfoDTO {
    @JsonProperty(value = "ID_USER")
    public Long idUser;

    @JsonProperty(value = "ID_SCHOOL")
    public Long idSchool;

    @JsonProperty(value = "TYPE_USER")
    public Long typeUser;

    @JsonProperty(value = "USERNAME")
    public String username;

    @JsonProperty(value = "PASSWORD")
    public String password;

    @JsonProperty(value = "EMAIL")
    public String email;

    @JsonProperty(value = "PHONE")
    public String phone;

    @JsonProperty(value = "GRADE")
    public Long grade;

    @JsonProperty(value = "GENDER")
    public Long gender;

    @JsonProperty(value = "BOD")
    public LocalDate bod;

    @JsonProperty(value = "ADDRESS")
    public String address;

    @JsonProperty(value = "AVATAR")
    public String avatar;

    @JsonProperty(value = "DESCRIPTION")
    public String description;

    @JsonProperty(value = "FACEBOOK")
    public String facebook;

    @JsonProperty(value = "INSTAGRAM")
    public String instagram;

    @JsonProperty(value = "ACTIVE")
    public Boolean active;

    @JsonProperty(value = "SCHOOL_INFO")
    public SchoolDTO schoolInfo;

    @JsonProperty(value = "ID_COURSE")
    public Long idCourse;

    @JsonProperty(value = "COURSE_NAME")
    public String courseName;
}
