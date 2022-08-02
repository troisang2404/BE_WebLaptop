package fit.nlu.weblaptop.service.impl;

import fit.nlu.weblaptop.dto.OrderDetailDto;
import fit.nlu.weblaptop.dto.OrderDto;
import fit.nlu.weblaptop.entity.OrderDetailEntity;
import fit.nlu.weblaptop.entity.OrdersEntity;
import fit.nlu.weblaptop.entity.UserEntity;
import fit.nlu.weblaptop.repository.OrderDetailRepository;
import fit.nlu.weblaptop.repository.OrdersRepository;
import fit.nlu.weblaptop.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class OrderServiceImpl implements OrdersService {
    @Autowired
    private OrdersRepository ordersRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Override
    public void save(OrdersEntity ordersEntity) {
        ordersRepository.save(ordersEntity);
    }

    @Override
    public List<OrderDto> getOrders(UserEntity user) {
        List<OrdersEntity> ordersEntity = ordersRepository.findByUser(user);
        List<OrderDto> orderDtoList = new ArrayList<>();
        ordersEntity.forEach(orders -> {
            List<OrderDetailEntity> orderDetailList = orderDetailRepository.findByOrders(orders);
            List<OrderDetailDto> orderDetailItems = new ArrayList<>();
            for (OrderDetailEntity orderDetail : orderDetailList) {
                OrderDetailDto orderDetailItemDto = new OrderDetailDto(orderDetail);
                orderDetailItems.add(orderDetailItemDto);
            }
            OrderDto orderDto = new OrderDto();
            orderDto.setFullName(orders.getUser().getName());
            orderDto.setPhone(orders.getPhone());
            orderDto.setNote(orders.getNote());
            orderDto.setTotal(orders.getTotal());
            orderDto.setStatus(orders.getStatus());
            orderDto.setOrderDetail(orderDetailItems);
            orderDto.setAddress(orders.getAddressToString());
            orderDtoList.add(orderDto);
        });

        return orderDtoList;
    }

}
