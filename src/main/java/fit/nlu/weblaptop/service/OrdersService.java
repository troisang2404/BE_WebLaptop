package fit.nlu.weblaptop.service;

import fit.nlu.weblaptop.dto.OrderDto;
import fit.nlu.weblaptop.entity.OrdersEntity;
import fit.nlu.weblaptop.entity.UserEntity;

import java.util.List;

public interface OrdersService {
    void save(OrdersEntity ordersEntity);
    OrdersEntity findOneById(Long id);
    OrdersEntity findByUser(UserEntity userEntity);
    List<OrderDto> getOrders(UserEntity user);
//    List<OrderDetailEntity> getOrderDetail(OrdersEntity ordersEntity);
}
