package fit.nlu.weblaptop.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@Entity
@Table(name = "user",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email")
        })
public class UserEntity extends Auditable {

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
    /**
     * Khóa ngoại
     */
    @ManyToMany
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<RoleEntity> roles = new HashSet<>();

    public UserEntity() {
    }

    public UserEntity(String username, String email, String password, String name, String phone, Integer status) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.status = status;
    }
}
