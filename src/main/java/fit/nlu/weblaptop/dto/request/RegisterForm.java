package fit.nlu.weblaptop.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;


@Getter
@Setter
public class RegisterForm {
    @NotBlank
    @Size(max = 20)
    private String username; //tên đăng nhập
    @NotBlank
    @Size(max = 50)
    private String email;       //Email
    @NotBlank
    @Size(max = 150)
    private String password;    //Mật khẩu

    private String name;        //Tên
    private String phone;       //Số điện thoại
    private Integer status;     //Trạng thái

    private Set<String> roles;

}
