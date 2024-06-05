package cfm.onthi.sercurity;
import cfm.onthi.constant.Role;
import cfm.onthi.dtos.base.InputCondition;
import cfm.onthi.dtos.UserInfoDTO;
import cfm.onthi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;
    private Role role;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDetails loadUserByEmail(String email) throws UsernameNotFoundException {
        InputCondition inputCondition = new InputCondition();
        inputCondition.EMAIL = email;
        UserInfoDTO userInfo = userRepository.getByInputCondition(inputCondition);
        if (userInfo == null) {
            throw new UsernameNotFoundException("Không tìm thấy người dùng!");
        }
        return new User(userInfo.getUsername(), userInfo.getPassword(), mapRolesToAuthorities(userInfo));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(UserInfoDTO userInfo) {
        // Map TYPE_USER to authorities here
        Long typeUser = userInfo.getTypeUser();
        if (typeUser == role.ADMIN.getCode()) {
            return Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }
        if (typeUser == role.STUDENT.getCode()) {
            return Collections.singleton(new SimpleGrantedAuthority("ROLE_STUDENT"));
        }
        else {
            // For other types, assuming they have ROLE_USER
            return Collections.singleton(new SimpleGrantedAuthority("ROLE_TEACHER"));
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
