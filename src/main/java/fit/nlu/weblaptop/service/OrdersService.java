package fit.nlu.weblaptop.service;

import fit.nlu.weblaptop.dto.OrderDto;
import fit.nlu.weblaptop.entity.OrdersEntity;
import fit.nlu.weblaptop.entity.UserEntity;

import java.util.List;

public interface OrdersService {
    void save(OrdersEntity ordersEntity);
    List<OrderDto> getOrders(UserEntity user);
}
