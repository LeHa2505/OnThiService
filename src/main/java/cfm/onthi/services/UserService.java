package cfm.onthi.services;

import cfm.onthi.dtos.UserInfoDTO;
import cfm.onthi.dtos.base.InputCondition;
import cfm.onthi.dtos.base.ResponseDTO;
import cfm.onthi.repositories.UserRepository;
import cfm.onthi.sercurity.JwtTokenProvider;
import org.jooq.Condition;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

public interface UserService {
    ResponseDTO getUserInfo(String email);
}

@Service
class UserServiceImpl extends BaseService implements UserService {
    private UserRepository userRepository;

    public UserServiceImpl(
            TransactionTemplate transactionTemplate,
            UserRepository userRepository
    ) {
        super();
        this.userRepository = userRepository;
    }

    @Override
    public ResponseDTO getUserInfo(String email) {
        InputCondition inputCondition = new InputCondition();
        inputCondition.EMAIL = email;
        UserInfoDTO userInfoDTO = userRepository.getByEmail(inputCondition);
        return new ResponseDTO(true, "Lấy dữ liệu thành công!", userInfoDTO);
    }
}
