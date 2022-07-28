package fit.nlu.weblaptop.dto;

import fit.nlu.weblaptop.entity.CartEntity;
import fit.nlu.weblaptop.entity.ProductEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItemDto {
    private Long id;
    private Integer quantity;
    private ProductEntity product;

    public CartItemDto() {
    }
    public CartItemDto(CartEntity cart) {
        this.id = cart.getId();
        this.quantity = cart.getQuantity();
        this.setProduct(cart.getProduct());
    }

}
