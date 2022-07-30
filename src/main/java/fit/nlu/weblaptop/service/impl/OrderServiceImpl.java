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
    public OrdersEntity findOneById(Long id) {
        return ordersRepository.findOneById(id);
    }

    @Override
    public OrdersEntity findByUser(UserEntity userEntity) {
        return ordersRepository.findByUser(userEntity);
    }

    @Override
    public OrderDto getOrders(UserEntity user) {
        OrdersEntity ordersEntity = ordersRepository.findByUser(user);
        List<OrderDetailEntity> orderDetailList = orderDetailRepository.findByOrders(ordersEntity);
        List<OrderDetailDto> orderDetailItems = new ArrayList<>();
        for (OrderDetailEntity orderDetail : orderDetailList) {
            OrderDetailDto orderDetailItemDto = new OrderDetailDto(orderDetail);
            orderDetailItems.add(orderDetailItemDto);
        }
        OrderDto orderDto = new OrderDto();
        orderDto.setFullName(ordersEntity.getUser().getName());
        orderDto.setPhone(ordersEntity.getPhone());
        orderDto.setNote(ordersEntity.getNote());
        orderDto.setTotal(ordersEntity.getTotal());
        orderDto.setStatus(ordersEntity.getStatus());
        orderDto.setOrderDetail(orderDetailItems);
        orderDto.setAddress(ordersEntity.getAddress());
        return orderDto;
    }

    @Override
    public List<OrderDetailEntity> getOrderDetail(OrdersEntity orders) {
        return orderDetailRepository.findByOrders(orders);
    }
}
