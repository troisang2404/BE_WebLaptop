package fit.nlu.weblaptop.repository;

import fit.nlu.weblaptop.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<OrdersEntity, Long> {

    OrdersEntity findOneByUser(UserEntity user);

    @Query("select distinct b from OrdersEntity b left join fetch b.orders p")
    List<OrdersEntity> findAllFetchEager();
}
