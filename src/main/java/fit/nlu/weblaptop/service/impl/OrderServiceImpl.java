package fit.nlu.weblaptop.service.impl;

import fit.nlu.weblaptop.entity.OrdersEntity;
import fit.nlu.weblaptop.repository.OrdersRepository;
import fit.nlu.weblaptop.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrdersService {
    @Autowired
    private OrdersRepository ordersRepository;

    @Override
    public void save(OrdersEntity ordersEntity) {
        ordersRepository.save(ordersEntity);
    }
}
