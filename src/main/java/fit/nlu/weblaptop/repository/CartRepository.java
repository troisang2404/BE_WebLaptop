package fit.nlu.weblaptop.repository;

import fit.nlu.weblaptop.entity.CartEntity;
import fit.nlu.weblaptop.entity.ProductEntity;
import fit.nlu.weblaptop.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<CartEntity, Long> {
    List<CartEntity> findByUser(UserEntity userEntity);

    CartEntity findOneById(Long id);

    CartEntity findOneByProduct(ProductEntity productEntity);

    void deleteByUser(UserEntity userEntity);
}
