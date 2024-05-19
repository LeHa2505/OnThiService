package cfm.onthi.controllers;

import cfm.onthi.exceptions.GlobalExceptionHandlerController;
import cfm.onthi.services.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/service-onthi")
@RequiredArgsConstructor
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandlerController.class);

    @Autowired
    private UserService userService;

    @GetMapping("/getUserInfo")
    public ResponseEntity<?> getUserInfo(@RequestParam(name = "email", required = false) String email) {
        return ResponseEntity.status(HttpStatus.OK).body((userService.getUserInfo(email)));
    }
}
