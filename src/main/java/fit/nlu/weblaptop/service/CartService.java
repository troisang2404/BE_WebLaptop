package fit.nlu.weblaptop.service;

import fit.nlu.weblaptop.entity.CartEntity;
import fit.nlu.weblaptop.entity.ProductEntity;
import fit.nlu.weblaptop.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface CartService {
    void save(CartEntity cartEntity);

    List<CartEntity> findAllByUser(Optional<UserEntity> userEntity);

    void deleteById(Long id);

    CartEntity findOneById(Long id);

    Double totalPrice(Optional<UserEntity> userEntity);

    CartEntity findOneByProduct(ProductEntity productEntity);

    void deleteByUser(UserEntity userEntity);
}
