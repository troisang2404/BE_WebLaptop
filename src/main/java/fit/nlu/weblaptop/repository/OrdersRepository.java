package fit.nlu.weblaptop.repository;

import fit.nlu.weblaptop.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface OrdersRepository extends JpaRepository<OrdersEntity, Long> {
    @Query("select distinct b from OrdersEntity b left join fetch b.orders p")
    OrdersEntity findOneById(Long id);
    OrdersEntity findByUser(UserEntity user);
}
