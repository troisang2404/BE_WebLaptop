package fit.nlu.weblaptop.service;

import fit.nlu.weblaptop.dto.CartDto;
import fit.nlu.weblaptop.entity.CartEntity;
import fit.nlu.weblaptop.entity.ProductEntity;
import fit.nlu.weblaptop.entity.UserEntity;

import java.util.List;

public interface CartService {
    void save(CartEntity cartEntity);

    List<CartEntity> findAllByUser(UserEntity userEntity);

    CartDto listCartItems(UserEntity user);

    void deleteById(Long id);

    CartEntity findOneById(Long id);

    Double totalCost(UserEntity userEntity);

    CartEntity findOneByProduct(ProductEntity productEntity);

    void deleteByUser(UserEntity userEntity);
}
