package fit.nlu.weblaptop.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CartDto {
    private List<CartItemDto> cartItems;
    private double totalCost;

    public CartDto() {
    }

}
