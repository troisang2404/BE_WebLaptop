package fit.nlu.weblaptop.controller.web;

import fit.nlu.weblaptop.entity.ERole;
import fit.nlu.weblaptop.entity.RoleEntity;
import fit.nlu.weblaptop.entity.UserEntity;
import fit.nlu.weblaptop.payload.request.LoginForm;
import fit.nlu.weblaptop.payload.response.JwtResponse;
import fit.nlu.weblaptop.payload.response.ResponseObject;
import fit.nlu.weblaptop.payload.request.RegisterForm;
import fit.nlu.weblaptop.security.jwt.JwtTokenProvider;
import fit.nlu.weblaptop.security.service.UserDetailsImpl;
import fit.nlu.weblaptop.service.RoleService;
import fit.nlu.weblaptop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequestMapping("/auth")
@RestController
public class AuthController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterForm registerForm) {
        if (userService.existsByUsername(registerForm.getUsername())) {
            return ResponseEntity.ok(new ResponseObject("", "The username existed! Please try again!", ""));
        }
        if (userService.existsByEmail(registerForm.getEmail())) {
            return ResponseEntity.ok(new ResponseObject("", "The email existed! Please try again!", ""));
        }
        UserEntity user = new UserEntity(
                registerForm.getUsername(),
                registerForm.getEmail(),
                passwordEncoder.encode(registerForm.getPassword()),
                registerForm.getName(),
                registerForm.getPhone(),
                1
        );
        Set<String> strRoles = registerForm.getRoles();
        Set<RoleEntity> roles = new HashSet<>();
        if (strRoles == null) {
            RoleEntity userRole = roleService.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        RoleEntity adminRole = roleService.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);
                        break;
                    default:
                        RoleEntity userRole = roleService.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }
        user.setRoles(roles);
        userService.save(user);
        return ResponseEntity.ok(new ResponseObject("", "User registered successfully!", ""));

    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginForm loginForm) {
        // Xác thực từ username và password.
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginForm.getUsername(),
                        loginForm.getPassword())
        );
        // Nếu không xảy ra exception tức là thông tin hợp lệ
        // Set thông tin authentication vào Security Context
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // Trả về jwt cho người dùng.
        String token = jwtTokenProvider.createToken(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        return ResponseEntity.ok(new JwtResponse(
                token,
                userDetails.getUsername(),
                userDetails.getName(),
                userDetails.getEmail(),
                userDetails.getAuthorities()
        ));
    }
    @GetMapping("/hello")
    public String adminAccess() {
        return "Hello World";
    }
}
