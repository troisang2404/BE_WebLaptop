package fit.nlu.weblaptop.controller.web;

import fit.nlu.weblaptop.dto.response.ResponseObject;
import fit.nlu.weblaptop.entity.*;
import fit.nlu.weblaptop.repository.VillageRepository;
import fit.nlu.weblaptop.service.CartService;
import fit.nlu.weblaptop.service.OrdersService;
import fit.nlu.weblaptop.service.UserService;
import fit.nlu.weblaptop.utils.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
public class OrderController {
    @Autowired
    private UserService userService;
    @Autowired
    private CartService cartService;
    @Autowired
    private OrdersService ordersService;
    @Autowired
    private VillageRepository villageRepository;

    @GetMapping("/order")
    public ResponseEntity<?> getOrder(@ModelAttribute("order") OrdersEntity ordersEntity) {
        if (SecurityUtil.getPrincipal() == null) {
            return ResponseEntity.ok(new ResponseObject("", "Bạn cần đang nhập", ""));
        } else {
            UserEntity userEntity = userService.findOneByUsername(SecurityUtil.getPrincipal().getUsername());
//            model.addAttribute("listCart", cartService.findAllByUser(userEntity));
            return ResponseEntity.ok(new ResponseObject("", "", cartService.totalPrice(userEntity)));
        }
    }

    @PostMapping("/order")
    public ResponseEntity<?> order(OrdersEntity ordersEntity, OrderDetailEntity orderDetailEntity) {
        if (SecurityUtil.getPrincipal() == null) {
            return ResponseEntity.ok(new ResponseObject("", "Bạn cần đang nhập", ""));
        } else {
            UserEntity userEntity = userService.findOneByUsername(SecurityUtil.getPrincipal().getUsername());
            ordersEntity.setUser(userEntity);
            List<CartEntity> listCart = cartService.findAllByUser(userEntity);
            List<OrderDetailEntity> detailEntityList = new ArrayList<>();
            double totalPrice = 0;
            for (CartEntity cart : listCart) {
                orderDetailEntity.setPrice(cart.getProduct().getSalePrice());
                orderDetailEntity.setAmount(cart.getQuantity());
                orderDetailEntity.setProduct(cart.getProduct());
                orderDetailEntity.setOrders(ordersEntity);
                detailEntityList.add(orderDetailEntity);

                totalPrice += cart.getQuantity() * cart.getProduct().getSalePrice();

            }
            ordersEntity.setTotal(totalPrice);
            ordersEntity.setStatus(0);
            ordersEntity.setOrders(detailEntityList);

            VillageEntity villageEntity = villageRepository.findOneById(ordersEntity.getAddress().getVillage().getId());
            ordersEntity.getAddress().setVillage(villageEntity);
            ordersEntity.getAddress().setOrders(ordersEntity);

            ordersService.save(ordersEntity);
            cartService.deleteByUser(userEntity);

            return ResponseEntity.ok(new ResponseObject("", "", ordersEntity));
        }
    }
}
