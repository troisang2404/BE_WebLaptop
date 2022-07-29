package fit.nlu.weblaptop.service.impl;

import fit.nlu.weblaptop.entity.OrdersEntity;
import fit.nlu.weblaptop.entity.UserEntity;
import fit.nlu.weblaptop.repository.OrdersRepository;
import fit.nlu.weblaptop.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrdersService {
    @Autowired
    private OrdersRepository ordersRepository;

    @Override
    public void save(OrdersEntity ordersEntity) {
        ordersRepository.save(ordersEntity);
    }

    @Override
    public List<OrdersEntity> findAllFetchEager() {
        return ordersRepository.findAllFetchEager();
    }

    @Override
    public OrdersEntity findOneByUser(UserEntity user) {
        return ordersRepository.findOneByUser(user);
    }
}
