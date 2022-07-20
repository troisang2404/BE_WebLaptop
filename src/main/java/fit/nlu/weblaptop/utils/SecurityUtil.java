package fit.nlu.weblaptop.utils;

import fit.nlu.weblaptop.security.service.UserDetailsImpl;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtil {
    public static UserDetailsImpl getPrincipal() {
        try {
            return (UserDetailsImpl) (SecurityContextHolder.getContext()).getAuthentication().getPrincipal();
        } catch (Exception e) {
            return null;
        }
    }
}
