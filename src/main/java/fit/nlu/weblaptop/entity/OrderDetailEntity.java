package fit.nlu.weblaptop.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@Entity
@Table(name = "order_detail")
public class OrderDetailEntity extends Auditable {

    private Integer amount;     //Số lượng sản phẩm
    private Double price;       //Giá của một sản phẩm

    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrdersEntity orders;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity product;
}
