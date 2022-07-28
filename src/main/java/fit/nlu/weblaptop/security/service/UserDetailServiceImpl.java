package fit.nlu.weblaptop.security.service;

import fit.nlu.weblaptop.entity.UserEntity;
import fit.nlu.weblaptop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Kiểm tra xem user có tồn tại trong database không?
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found -> username or password" + username));
        return UserDetailsImpl.build(user);
    }

    //hàm lấy ra User hiện tại để thực hiện thao tác với DB
    public UserEntity getCurrentUser() {
        Optional<UserEntity> user;
        String username;
        //lấy 1 object trong principal trong securityContextHolder
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //so sánh obj với UserDetails nếu mà đúng thì gán username = principal.getUsername()
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            //nếu không phải username hiện tại thì username = principal.toString()
            username = principal.toString();
        }
        //kiểm tra nếu username tồn tại trong DB thì gán user = hàm tìm kiếm trong DB theo username đó
        if (userRepository.existsByUsername(username)) {
            user = userRepository.findByUsername(username);
        } else {
            //nếu chưa tồn tại thì trả về 1 thể hiện của lớp User thông qua Optional.of
            user = Optional.of(new UserEntity());
            //set cho nó 1 cái tên ẩn danh, đây là trường hợp mà tương tác đăng nhập kiểu FB hay GG
            user.get().setUsername("Anonymous");
        }
        return user.get();
    }
}
