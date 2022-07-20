package fit.nlu.weblaptop.controller.web;

import fit.nlu.weblaptop.entity.CartEntity;
import fit.nlu.weblaptop.entity.UserEntity;
import fit.nlu.weblaptop.payload.response.ResponseObject;
import fit.nlu.weblaptop.service.CartService;
import fit.nlu.weblaptop.service.ProductService;
import fit.nlu.weblaptop.service.UserService;
import fit.nlu.weblaptop.utils.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000/")
public class CartController {
    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Autowired
    private CartService cartService;

    @GetMapping("/cart")
    public ResponseEntity<?> showCart() {
//        if (SecurityUtil.getPrincipal() == null) {
//            return ResponseEntity.ok(new ResponseObject("ok","Bạn cần đăng nhập",""));
//        } else {
            Optional<UserEntity> userEntity = userService.findByUsername(SecurityUtil.getPrincipal().getUsername());
            List<CartEntity> listCart = cartService.findAllByUser(userEntity);
            return ResponseEntity.ok(new ResponseObject("ok","Giỏ hàng",userEntity));
//        }
    }
}
