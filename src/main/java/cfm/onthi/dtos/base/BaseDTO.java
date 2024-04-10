package cfm.onthi.dtos.base;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseDTO {
    @JsonProperty(value = "NGAY_SUA")
    public Date ngaySua = new Date();

    @JsonProperty(value = "NGAY_TAO")
    public Date ngayTao = new Date();

    @JsonProperty(value = "NGUOI_SUA")
    public String nguoiSua;

    @JsonProperty(value = "NGUOI_TAO")
    public String nguoiTao;

    @JsonProperty(value = "MA_CNANG")
    public String maCnang;
}
