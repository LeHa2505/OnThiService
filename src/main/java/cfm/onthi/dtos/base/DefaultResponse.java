package cfm.onthi.dtos.base;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DefaultResponse {
    @JsonProperty(value = "TYPE")
    public String type;

    @JsonProperty(value = "MESSAGE")
    public String message;
}
