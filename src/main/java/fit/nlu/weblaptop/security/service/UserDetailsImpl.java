package fit.nlu.weblaptop.security.service;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fit.nlu.weblaptop.entity.UserEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class UserDetailsImpl implements UserDetails {
    private Long id;
    private String username; //tên đăng nhập
    private String email;       //Email
    @JsonIgnore
    private String password;    //Mật khẩu
    private String name;        //Tên
    private String phone;       //Số điện thoại
    private Integer status;     //Trạng thái
    private Collection<? extends GrantedAuthority> roles;

    public UserDetailsImpl(Long id, String username, String email, String password, String name, String phone, Integer status, Collection<? extends GrantedAuthority> roles) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.status = status;
        this.roles = roles;
    }


    public static UserDetailsImpl build(UserEntity user){
        List<GrantedAuthority> authorities=user.getRoles().stream().map(role->
                new SimpleGrantedAuthority(role.getName().name())).collect(Collectors.toList());
        return new UserDetailsImpl(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                user.getName(),
                user.getPhone(),
                user.getStatus(),
                authorities
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
