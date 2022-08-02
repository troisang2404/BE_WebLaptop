package fit.nlu.weblaptop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fit.nlu.weblaptop.dto.OrderDetailDto;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<OrderDetailEntity> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderDetailEntity> orders) {
        this.orders = orders;
    }

    public AddressEntity getAddress() {
        return address;
    }

    public void setAddress(AddressEntity address) {
        this.address = address;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
