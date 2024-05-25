package cfm.onthi.services;

import cfm.onthi.dtos.AuthResponseDTO;
import cfm.onthi.dtos.LoginDTO;
import cfm.onthi.dtos.RegisterDTO;
import cfm.onthi.dtos.base.InputCondition;
import cfm.onthi.dtos.base.ResponseDTO;
import cfm.onthi.dtos.UserInfoDTO;
import cfm.onthi.entities.tables.OtUser;
import cfm.onthi.exceptions.CustomException;
import cfm.onthi.repositories.UserRepository;
import cfm.onthi.sercurity.JwtTokenProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

public interface AuthService {
    ResponseDTO login(LoginDTO loginDTO) throws NoSuchAlgorithmException, InvalidKeySpecException;

    ResponseDTO registerService(RegisterDTO registerDTO);
}

@Lazy
@Service
class AuthServiceImpl extends BaseService implements AuthService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final TransactionTemplate transactionTemplate;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private JwtTokenProvider jwtTokenProvider;

    public AuthServiceImpl(
            TransactionTemplate transactionTemplate, UserRepository userRepository,
            PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider
    ) {
        super();
        this.transactionTemplate = transactionTemplate;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public ResponseDTO login(LoginDTO loginDTO) throws NoSuchAlgorithmException, InvalidKeySpecException {
        // Lấy thông tin người dùng từ cơ sở dữ liệu
        InputCondition inputCondition = new InputCondition();
        inputCondition.EMAIL = loginDTO.email;
        inputCondition.PASSWORD = loginDTO.password;
        UserInfoDTO userInfo = userRepository.getByInputCondition(inputCondition);
        if (userInfo == null || !passwordEncoder.matches(loginDTO.password, userInfo.getPassword())) {
            return new ResponseDTO(false, "Sai email hoặc mật khẩu! Vui lòng nhập lại!", null);
        }
        AuthResponseDTO authResponseDTO = new AuthResponseDTO();
        userInfo.password = "";
        authResponseDTO.setUserInfoDTO(userInfo);
        authResponseDTO.setAccessToken(jwtTokenProvider.generateToken(userInfo.email, userInfo.typeUser.toString(),"ACCESS_TOKEN"));
        authResponseDTO.setRefreshToken(jwtTokenProvider.generateToken(userInfo.email, userInfo.typeUser.toString(),"REFRESH_TOKEN"));
        // Thông tin đăng nhập hợp lệ, tạo token cho người dùng
        return new ResponseDTO(true, "Đăng nhập thành công!", authResponseDTO);
    }

    public ResponseDTO registerService(RegisterDTO registerDTO) {
        if (userRepository.existsByEmail(registerDTO.getEmail())) {
            ResponseDTO responseDTO = new ResponseDTO(false, "Tài khoản này đã tồn tại!", null);
            return responseDTO;
        }

        UserInfoDTO userInfoDTO = new UserInfoDTO();
        userInfoDTO.setTypeUser(registerDTO.getTypeUser());
        userInfoDTO.setUsername(registerDTO.getUsername());
        userInfoDTO.setEmail(registerDTO.getEmail());
        userInfoDTO.setPassword(passwordEncoder.encode(registerDTO.getPassword()));

        if (userRepository.save(userInfoDTO)) {
            return new ResponseDTO(true, "Đăng ký tài khoản thành công", null);
        }

        return new ResponseDTO(false, "Đăng ký tài khoản không thành công. Có lỗi xảy ra!", null);
    }
}
