package cfm.onthi.dtos.base;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OutputError {
    @JsonProperty(value = "FILE_NAME")
    public String fileName;
    @JsonProperty(value = "CLASS_NAME")
    public String className;
    @JsonProperty(value = "METHOD_NAME")
    public String methodName;
    @JsonProperty(value = "LINE_ERROR")
    public Integer lineError;
}
