package fit.nlu.weblaptop.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
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

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public OrdersEntity getOrders() {
        return orders;
    }

    public void setOrders(OrdersEntity orders) {
        this.orders = orders;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }
}
