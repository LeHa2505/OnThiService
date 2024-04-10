package cfm.onthi.dtos.base;

import lombok.Data;

@Data
public class ResponseDTO {

    public boolean success;
    public String message;
    public Object data;

    public ResponseDTO() {
    }

    public ResponseDTO(boolean success, String message, Object data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public static ResponseDTO ok(Object data) {
        return new ResponseDTO(true, "OK", data);
    }

    public static ResponseDTO ok(String message) {
        return new ResponseDTO(true, message, null);
    }

    public static ResponseDTO error() {
        return new ResponseDTO(false, "error", null);
    }

    public static ResponseDTO error(String message) {
        return new ResponseDTO(false, message, null);
    }
}
