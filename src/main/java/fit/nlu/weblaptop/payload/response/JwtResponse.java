package fit.nlu.weblaptop.payload.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Collection;

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
    private Collection<? extends GrantedAuthority> roles;

    public JwtResponse(String token, String username, String email, String name, Collection<? extends GrantedAuthority> authorities) {
        this.token = token;
        this.username = username;
        this.email = email;
        this.name = name;
        this.roles = authorities;
    }
}
