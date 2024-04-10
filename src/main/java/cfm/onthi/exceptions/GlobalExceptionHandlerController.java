package cfm.onthi.exceptions;

import cfm.onthi.dtos.base.OutputError;
import cfm.onthi.dtos.base.ResponseDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandlerController {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandlerController.class);

    @Bean
    public ErrorAttributes errorAttributes() {
        // Hide exception field in the return object
        return new DefaultErrorAttributes() {
            @Override
            public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions options) {
                return super.getErrorAttributes(webRequest, ErrorAttributeOptions.defaults().excluding(ErrorAttributeOptions.Include.EXCEPTION));
            }
        };
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ResponseDTO> handleCustomException(CustomException ex) {
        ResponseDTO responseDTO = new ResponseDTO(false, ex.getMessage(), null);

        logger.error("Có lỗi xả ra với service On Thi: ", ex);

        return ResponseEntity.status(ex.getHttpStatus()).body(responseDTO);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDTO> handleException(Exception ex) {
        ResponseDTO responseDTO = new ResponseDTO();

        switch (ex.getClass().getSimpleName()) {
            case "AccessDeniedException":
                responseDTO = new ResponseDTO(false, "An error occurred regarding authentication and authorization of the On Thi service: " + ex.getMessage(), null);
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(responseDTO);
            case "SQLException":
                SQLException sqlException = findSQLExceptionInChain(ex);
                responseDTO = new ResponseDTO(false, "An error occurred in the SQL processing of the On Thi service: " + sqlException.getMessage(), null);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDTO);
            default:
                StackTraceElement[] stackTraceElement = ex.getStackTrace();

                if (stackTraceElement != null && stackTraceElement.length > 0) {
                    OutputError outputError = new OutputError(stackTraceElement[0].getFileName(), stackTraceElement[0].getClassName(), stackTraceElement[0].getMethodName(), stackTraceElement[0].getLineNumber());
                    responseDTO = new ResponseDTO(false, "An error occurred in the processing logic of the On Thi service: " + ex.getMessage(), outputError);
                } else {
                    responseDTO = new ResponseDTO(false, "An error occurred in the processing logic of the On Thi service: " + ex.getMessage(), null);
                }

                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDTO);
        }
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDTO> handleInvalidArgument(MethodArgumentNotValidException ex, HttpServletResponse res) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });

        ObjectMapper mapper = new ObjectMapper();
        String jsonResult;
        try {
            jsonResult = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(errors);

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDTO(false, HttpStatus.BAD_REQUEST.name(), mapper.readValue(jsonResult, Object.class)));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleBindException(BindException e) {
        if (e.getBindingResult().hasErrors()) {
            e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        }

        return "Request is invalid";
    }

    private SQLException findSQLExceptionInChain(Throwable throwable) {
        while (throwable != null) {
            if (throwable instanceof SQLException) {
                return (SQLException) throwable;
            }
            throwable = throwable.getCause();
        }
        return null;
    }
}
