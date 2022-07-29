package fit.nlu.weblaptop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@Entity
@Table(name = "orders")
public class OrdersEntity extends Auditable {

    private String phone; //Số điện thoại
    private String note; //Ghi chú
    private Double total; //Tổng giá trị đơn hàng
    private Integer status; //Trạng thái đơn hàng: 0-Chưa xác nhận | 1-Đã xác nhận

    /**
     * Khóa ngoại
     */
//    @JsonIgnore
    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<OrderDetailEntity> orders; //Danh sách chi tiết đơn đặt hàng

    @OneToOne(mappedBy = "orders", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private AddressEntity address; //Địa chỉ giao hàng

    @JsonIgnore
    @ManyToOne
    private UserEntity user; //Người đặt hàng

    public String getAddressToString() {
        return address.getDetail() + ", " + address.getVillage().getName() + ", " + address.getVillage().getDistrict().getName() + ", " + address.getVillage().getDistrict().getProvince().getName();
    }
}
