package fit.nlu.weblaptop.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@Entity
@Table(name = "user")
public class UserEntity extends Auditable {

    private String email;       //Email
    private String password;    //Mật khẩu
    @Transient
    private String repassword;
    private String name;        //Tên
    private String phone;       //Số điện thoại
    private Integer status;     //Trạng thái
    /**
     * Khóa ngoại
     */
    @ManyToMany
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "userid"), inverseJoinColumns = @JoinColumn(name = "roleid"))
    private Set<RoleEntity> roles = new HashSet<>();

    public UserEntity() {
    }
    public UserEntity(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
