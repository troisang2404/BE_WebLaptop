package fit.nlu.weblaptop.service.impl;

import fit.nlu.weblaptop.entity.CartEntity;
import fit.nlu.weblaptop.entity.ProductEntity;
import fit.nlu.weblaptop.entity.UserEntity;
import fit.nlu.weblaptop.repository.CartRepository;
import fit.nlu.weblaptop.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    CartRepository cartRepository;

    @Override
    public void save(CartEntity cartEntity) {
        cartRepository.save(cartEntity);
    }

    @Override
    public List<CartEntity> findAllByUser(Optional<UserEntity> userEntity) {
        return cartRepository.findByUser(userEntity);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        cartRepository.deleteById(id);
    }

    @Override
    public CartEntity findOneById(Long id) {
        return cartRepository.findOneById(id);
    }

    @Override
    public Double totalPrice(Optional<UserEntity> userEntity) {
        List<CartEntity> list = cartRepository.findByUser(userEntity);
        double total = 0;
        for (CartEntity cart : list) {
            total += cart.getNumber() + cart.getProduct().getSalePrice();
        }
        return total;
    }

    @Override
    public CartEntity findOneByProduct(ProductEntity productEntity) {
        return cartRepository.findOneByProduct(productEntity);
    }

    @Override
    @Transactional
    public void deleteByUser(UserEntity userEntity) {
        cartRepository.deleteByUser(userEntity);
    }
}
