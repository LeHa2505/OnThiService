package cfm.onthi.sercurity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

@Data
@AllArgsConstructor
public class CustomGrantedAuthority implements GrantedAuthority {
    private String role;
    private List<String> permissions;

    @Override
    public String getAuthority() {
        return this.role;
    }
}
