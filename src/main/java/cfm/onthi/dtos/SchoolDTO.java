package cfm.onthi.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SchoolDTO {
    @JsonProperty(value = "ID_SCHOOL")
    public Long idSchool;

    @JsonProperty(value = "ID_PROVINCE")
    public Long idProvince;

    @JsonProperty(value = "SCHOOL_NAME")
    public String schoolName;

    @JsonProperty(value = "TYPE_SCHOOL")
    public Long typeSchool;

    @JsonProperty(value = "PROVINCE_INFO")
    public ProvinceDTO provinceInfo;
}
