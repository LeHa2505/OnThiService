package cfm.onthi.controllers;

import cfm.onthi.dtos.AuthResponseDTO;
import cfm.onthi.dtos.LoginDTO;
import cfm.onthi.dtos.RegisterDTO;
import cfm.onthi.sercurity.JwtTokenProvider;
import cfm.onthi.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;

@RestController
@RequestMapping("/service-auth")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthController {
    private AuthenticationManager authenticationManager;
    private PasswordEncoder passwordEncoder;
    private JwtTokenProvider jwtTokenProvider;
    private AuthService authService;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager,
                          PasswordEncoder passwordEncoder,
                          JwtTokenProvider jwtTokenProvider,
                          AuthService authService) {
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterDTO registerDTO) {
        return ResponseEntity.status(HttpStatus.OK).body((authService.registerService(registerDTO)));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) throws NoSuchAlgorithmException, InvalidKeySpecException {
        return ResponseEntity.status(HttpStatus.OK).body((authService.login(loginDTO)));
    }
}
