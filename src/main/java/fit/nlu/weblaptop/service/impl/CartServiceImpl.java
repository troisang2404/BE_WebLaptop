package fit.nlu.weblaptop.service.impl;

import fit.nlu.weblaptop.dto.CartDto;
import fit.nlu.weblaptop.dto.CartItemDto;
import fit.nlu.weblaptop.entity.CartEntity;
import fit.nlu.weblaptop.entity.ProductEntity;
import fit.nlu.weblaptop.entity.UserEntity;
import fit.nlu.weblaptop.repository.CartRepository;
import fit.nlu.weblaptop.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    CartRepository cartRepository;

    @Override
    public void save(CartEntity cartEntity) {
        cartRepository.save(cartEntity);
    }

    @Override
    public List<CartEntity> findAllByUser(UserEntity userEntity) {
        return cartRepository.findByUser(userEntity);
    }

    @Override
    public CartDto listCartItems(UserEntity user) {
        List<CartEntity> cartList = cartRepository.findByUser(user);
        List<CartItemDto> cartItems = new ArrayList<>();
        double totalCost = 0;
        for (CartEntity cart: cartList) {
            CartItemDto cartItemDto = new CartItemDto(cart);
            totalCost += cartItemDto.getQuantity() * cart.getProduct().getSalePrice();
            cartItems.add(cartItemDto);
        }

        CartDto cartDto = new CartDto();
        cartDto.setTotalCost(totalCost);
        cartDto.setCartItems(cartItems);
        return  cartDto;
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
    public Double totalPrice(UserEntity userEntity) {
        List<CartEntity> list = cartRepository.findByUser(userEntity);
        double total = 0;
        for (CartEntity cart : list) {
            total += cart.getQuantity() + cart.getProduct().getSalePrice();
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
