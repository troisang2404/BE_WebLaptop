package fit.nlu.weblaptop.dto;

import fit.nlu.weblaptop.entity.OrderDetailEntity;
import fit.nlu.weblaptop.entity.OrdersEntity;
import fit.nlu.weblaptop.entity.ProductEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDetailDto {
    private Integer amount;     //Số lượng sản phẩm
    private Double price;       //Giá của một sản phẩm
    private Long ordersId;
    private Long productId;
    private String productName;

    public OrderDetailDto() {
    }

    public OrderDetailDto(OrderDetailEntity orderDetail) {
        this.amount = orderDetail.getAmount();
        this.price = orderDetail.getPrice();
        this.ordersId = orderDetail.getOrders().getId();
        this.productId = orderDetail.getProduct().getId();
        this.productName = orderDetail.getProduct().getName();
    }
}
