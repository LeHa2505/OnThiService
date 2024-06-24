package cfm.onthi.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ShortDTO {
    @JsonProperty(value = "ID_SHORT")
    public Long id_short;

    @JsonProperty(value = "ID_USER")
    public Long id_user;

    @JsonProperty(value = "TITLE")
    public String title;

    @JsonProperty(value = "THUMBNAIL")
    public String thumbnail;

    @JsonProperty(value = "VIDEO_LINK")
    public String video_link;

    @JsonProperty(value = "DESCRIPTION")
    public String description;

    @JsonProperty(value = "CREATED_AT")
    public LocalDateTime created_at;

    @JsonProperty(value = "MODIFIED_AT")
    public LocalDateTime modified_at;

    @JsonProperty(value = "TEACHER_INFO")
    public UserInfoDTO teacher_info;
}
