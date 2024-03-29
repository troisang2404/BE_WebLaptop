package fit.nlu.weblaptop.controller.web;

import fit.nlu.weblaptop.dto.OrderDto;
import fit.nlu.weblaptop.dto.response.ResponseObject;
import fit.nlu.weblaptop.entity.*;
import fit.nlu.weblaptop.repository.VillageRepository;
import fit.nlu.weblaptop.service.CartService;
import fit.nlu.weblaptop.service.OrdersService;
import fit.nlu.weblaptop.service.UserService;
import fit.nlu.weblaptop.utils.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> getOrder() {
        if (SecurityUtil.getPrincipal() == null) {
            return ResponseEntity.ok(new ResponseObject("", "Bạn cần đang nhập", ""));
        } else {
            UserEntity user = userService.findOneByUsername(SecurityUtil.getPrincipal().getUsername());
            List<OrderDto> orders = ordersService.getOrders(user);
            return ResponseEntity.ok(orders);
        }
    }

    @PostMapping("/order")
    public ResponseEntity<?> order(@RequestBody OrdersEntity ordersEntity) {
        if (SecurityUtil.getPrincipal() == null) {
            return ResponseEntity.ok(new ResponseObject("", "Bạn cần đang nhập", ""));
        } else {
            UserEntity user = userService.findOneByUsername(SecurityUtil.getPrincipal().getUsername());
            ordersEntity.setUser(user);

            List<CartEntity> cartList = cartService.findAllByUser(user);
            List<OrderDetailEntity> detailEntityList = new ArrayList<>();
            double totalCost = 0;
            for (CartEntity cart : cartList) {
                OrderDetailEntity orderDetailEntity = new OrderDetailEntity();
                orderDetailEntity.setPrice(cart.getProduct().getSalePrice());
                orderDetailEntity.setAmount(cart.getQuantity());
                orderDetailEntity.setProduct(cart.getProduct());
                orderDetailEntity.setOrders(ordersEntity);
                detailEntityList.add(orderDetailEntity);

                totalCost += cart.getQuantity() * cart.getProduct().getSalePrice();
            }
            ordersEntity.setTotal(totalCost);
            ordersEntity.setStatus(0);
            ordersEntity.setOrders(detailEntityList);

            VillageEntity villageEntity = villageRepository.findOneById(ordersEntity.getAddress().getVillage().getId());
            ordersEntity.getAddress().setVillage(villageEntity);
            ordersEntity.getAddress().setOrders(ordersEntity);

            ordersService.save(ordersEntity);
            cartService.deleteByUser(user);

            return ResponseEntity.ok(new ResponseObject("ok", "Đặt hàng thành công", ""));
        }
    }
}
