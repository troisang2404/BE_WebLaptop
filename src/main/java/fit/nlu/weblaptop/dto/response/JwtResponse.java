package fit.nlu.weblaptop.dto.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private String username; //tên đăng nhập
    private String email;
    //    private String password;
    private String name;
    //    private String phone;
//    private Integer status;
    private List<String> roles;

    public JwtResponse(String token, String username, String email, String name, List<String> roles) {
        this.token = token;
        this.username = username;
        this.email = email;
        this.name = name;
        this.roles = roles;
    }
}
