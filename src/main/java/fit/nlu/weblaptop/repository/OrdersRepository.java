package fit.nlu.weblaptop.repository;

import fit.nlu.weblaptop.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OrdersRepository extends JpaRepository<OrdersEntity, Long> {
    @Query("select distinct b from OrdersEntity b left join fetch b.orders p")
    OrdersEntity findOneById(Long id);
    OrdersEntity findOneByUser(UserEntity user);
    List<OrdersEntity> findByUser(UserEntity user);
}
