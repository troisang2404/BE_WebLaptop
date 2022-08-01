package fit.nlu.weblaptop.controller.web;

import fit.nlu.weblaptop.dto.CartDto;
import fit.nlu.weblaptop.entity.CartEntity;
import fit.nlu.weblaptop.entity.ProductEntity;
import fit.nlu.weblaptop.entity.UserEntity;
import fit.nlu.weblaptop.dto.response.ResponseObject;
import fit.nlu.weblaptop.security.service.UserDetailServiceImpl;
import fit.nlu.weblaptop.service.CartService;
import fit.nlu.weblaptop.service.ProductService;
import fit.nlu.weblaptop.service.UserService;
import fit.nlu.weblaptop.utils.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        if (SecurityUtil.getPrincipal() == null) {
            return ResponseEntity.ok(new ResponseObject("failed", "Bạn cần đang nhập", ""));
        } else {
            UserEntity userEntity = userService.findOneByUsername(SecurityUtil.getPrincipal().getUsername());
            CartDto listCart = cartService.listCartItems(userEntity);
            return ResponseEntity.ok(listCart);
        }
    }

    @PostMapping("/addCart/{id}")
    public ResponseEntity<?> addCart(@PathVariable("id") Long id, CartEntity cartEntity ) {
//        CartEntity cartEntity = new CartEntity();
        if (SecurityUtil.getPrincipal() == null) {
            return ResponseEntity.ok(new ResponseObject("failed", "Bạn cần đăng nhập", null));
        } else {
            ProductEntity product = productService.findOneById(id);
            if (cartService.findOneByProduct(product) == null) {
                UserEntity user = userService.findOneByUsername(SecurityUtil.getPrincipal().getUsername());
                cartEntity.setProduct(product);
                cartEntity.setUser(user);
                cartEntity.setQuantity(1);
                cartService.save(cartEntity);
                return new ResponseEntity(new ResponseObject("ok", "Thêm thành công", null), HttpStatus.CREATED);
            } else {
                return ResponseEntity.ok(new ResponseObject("failed", "Đã được thêm vào giỏ hàng", null));
            }
        }
    }

    @PostMapping("/removeItem")
    @ResponseBody
    public ResponseEntity<?> removeItem(@RequestParam("id") Long id) {
        try {
            cartService.deleteById(id);
            return ResponseEntity.ok(new ResponseObject("", "Sản phẩm đã được xóa", id));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(new ResponseObject("", "Lỗi xoá sản phẩm", id));
        }
    }

    @PostMapping("/addItem")
    @ResponseBody
    public ResponseEntity<?> addItem(@RequestParam("id") Long id, @RequestParam("quantity") Integer quantity) {
        try {
            CartEntity cartEntity = cartService.findOneById(id);
            cartEntity.setQuantity(quantity);
            cartService.save(cartEntity);
            return ResponseEntity.ok(new ResponseObject("", "Cập nhật thành công", cartEntity.getProduct().getSalePrice() * quantity));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(new ResponseObject("", "Lỗi cập nhật số lượng", null));
        }
    }

}
