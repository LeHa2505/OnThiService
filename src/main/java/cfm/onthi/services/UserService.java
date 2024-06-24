package cfm.onthi.services;

import cfm.onthi.dtos.ProvinceDTO;
import cfm.onthi.dtos.SchoolDTO;
import cfm.onthi.dtos.ShortDTO;
import cfm.onthi.dtos.UserInfoDTO;
import cfm.onthi.dtos.base.InputCondition;
import cfm.onthi.dtos.base.ResponseDTO;
import cfm.onthi.repositories.ProvinceRepository;
import cfm.onthi.repositories.SchoolRepository;
import cfm.onthi.repositories.ShortRepository;
import cfm.onthi.repositories.UserRepository;
import cfm.onthi.sercurity.JwtTokenProvider;
import org.jooq.Condition;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;

public interface UserService {
    ResponseDTO getUserInfo(String email);
    ResponseDTO getAllUser();
    ResponseDTO updateUserInfo(UserInfoDTO userInfoDTO);
}

@Service
class UserServiceImpl extends BaseService implements UserService {
    private UserRepository userRepository;
    private SchoolRepository schoolRepository;
    private ProvinceRepository provinceRepository;
    private ShortRepository shortRepository;

    public UserServiceImpl(
            TransactionTemplate transactionTemplate,
            UserRepository userRepository,
            SchoolRepository schoolRepository,
            ProvinceRepository provinceRepository,
            ShortRepository shortRepository
    ) {
        super();
        this.userRepository = userRepository;
        this.schoolRepository = schoolRepository;
        this.provinceRepository = provinceRepository;
        this.shortRepository = shortRepository;
    }

    @Override
    public ResponseDTO getUserInfo(String email) {
        InputCondition inputCondition = new InputCondition();
        inputCondition.EMAIL = email;
        UserInfoDTO userInfoDTO = userRepository.getByEmail(inputCondition);
        return new ResponseDTO(true, "Lấy dữ liệu thành công!", userInfoDTO);
    }

    @Override
    public ResponseDTO getAllUser() {
        List<UserInfoDTO> userInfoDTOList = userRepository.getAll();
        return new ResponseDTO(true, "Lấy dữ liệu thành công!", userInfoDTOList);
    }

    @Override
    public ResponseDTO updateUserInfo(UserInfoDTO userInfoDTO) {
        InputCondition inputCondition = new InputCondition();
        inputCondition.PROVINCE_NAME = userInfoDTO.schoolInfo.provinceInfo.provinceName;

        ProvinceDTO provinceDTO = provinceRepository.getByInputCondition(inputCondition);
        userInfoDTO.schoolInfo.provinceInfo.provinceName = provinceDTO.provinceName;
        userInfoDTO.schoolInfo.idProvince = provinceDTO.idProvince;

        if (userRepository.update(userInfoDTO) && schoolRepository.update(userInfoDTO.schoolInfo)) {
            return new ResponseDTO(true, "Chỉnh sửa thông tin thành công!", null);
        }
        else
        {
            return new ResponseDTO(false, "Có lỗi xảy ra!", null);
        }
    }
}
