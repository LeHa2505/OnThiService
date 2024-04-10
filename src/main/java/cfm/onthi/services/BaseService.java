package cfm.onthi.services;

import cfm.onthi.dtos.base.ResponseDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.modelmapper.ModelMapper;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.time.format.DateTimeFormatter;

public class BaseService {
    ObjectMapper objectMapper = new ObjectMapper();
    DateTimeFormatter formatterddMMyyyy = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    DateTimeFormatter formatterddMMuuuu = DateTimeFormatter.ofPattern("dd/MM/uuuu");
    ModelMapper modelMapper = new ModelMapper();
    RestTemplate restTemplate = new RestTemplate();

    public BaseService() {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_NUMBERS_FOR_ENUMS, false);
        objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT, true);
    }

    public <TI, TO> ResponseDTO restMicroService(String serviceName, String api, HttpMethod method, TI inputDto, Class<TO> outputClass) {
        String url = serviceName + api;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<TI> requestEntity = null;
        if (inputDto != null) {
            // Chuyển đổi dữ liệu đầu vào thành JSON
            requestEntity = new HttpEntity<>(inputDto, headers);
        } else {
            requestEntity = new HttpEntity<>(headers);
        }

        ResponseEntity<TO> responseEntity = restTemplate.exchange(url, method, requestEntity, outputClass);

        HttpStatus httpStatus = (HttpStatus) responseEntity.getStatusCode();
        String responseMessage = httpStatus.getReasonPhrase();

        // Xử lý dữ liệu JSON trả về
        TO responseBody = responseEntity.getBody();

        // Chuyển đổi JSON thành đối tượng DTO
        TO outputDto = modelMapper.map(responseBody, outputClass);

        // Tạo ResponseDTO
        TO output = modelMapper.map(responseBody, outputClass);
        boolean success = httpStatus.is2xxSuccessful(); // Kiểm tra trạng thái thành công
        String message = success ? "Success" : responseMessage;
        return new ResponseDTO(success, message, output);
    }

    public <TI, TOSuccess, T0Error> ImmutablePair<TOSuccess, T0Error> restMicroService(String serviceName, String api, HttpMethod method, TI objIn, Class<TOSuccess> typeOutSuccess, Class<T0Error> typeOutError) throws JsonProcessingException {
        String url = serviceName + api;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<TI> httpEntity = new HttpEntity<>(objIn, headers);

        ResponseEntity<String> result = this.restTemplate.exchange(url, method, httpEntity, String.class);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false);
        mapper.configure(DeserializationFeature.FAIL_ON_NUMBERS_FOR_ENUMS, false);
        mapper.configure(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT, true);


        if (result.getBody().contains("NODATA") || result.getBody().contains("ERROR")) {
            return ImmutablePair.of(null, mapper.readValue(result.getBody(), typeOutError));
        } else {
            return ImmutablePair.of(mapper.readValue(result.getBody(), typeOutSuccess), null);
        }
    }
}

