package cfm.onthi.controllers;


import cfm.onthi.dtos.base.ResponseDTO;
import cfm.onthi.exceptions.GlobalExceptionHandlerController;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/service-onthi")
@RequiredArgsConstructor
public class OnThiController {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandlerController.class);

    @GetMapping("/testGetAPI")
    public ResponseEntity<?> tesGetAPI() {
        return ResponseEntity.status(HttpStatus.OK).body(ResponseDTO.ok("Call API Test Success 2"));
    }
}
