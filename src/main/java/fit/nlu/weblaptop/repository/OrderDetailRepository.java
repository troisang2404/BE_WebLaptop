package fit.nlu.weblaptop.repository;

import fit.nlu.weblaptop.entity.OrderDetailEntity;
import fit.nlu.weblaptop.entity.OrdersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetailEntity, Long> {
    List<OrderDetailEntity> findByOrders(OrdersEntity ordersEntity);

}
