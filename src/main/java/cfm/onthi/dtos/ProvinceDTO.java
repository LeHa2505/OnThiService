package cfm.onthi.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProvinceDTO {
    @JsonProperty(value = "ID_PROVINCE")
    public Long idProvince;

    @JsonProperty(value = "PROVINCE_NAME")
    public String provinceName;
}
