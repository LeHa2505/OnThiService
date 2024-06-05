package cfm.onthi.controllers;


import cfm.onthi.dtos.base.ResponseDTO;
import cfm.onthi.exceptions.GlobalExceptionHandlerController;
import cfm.onthi.services.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;


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
