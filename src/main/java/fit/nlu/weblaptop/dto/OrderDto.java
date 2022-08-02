package fit.nlu.weblaptop.dto;

import fit.nlu.weblaptop.entity.AddressEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderDto {
    private String fullName; //Tên người đặt hàng
    private String phone; //Số điện thoại
    private String note; //Ghi chú
    private Double total; //Tổng giá trị đơn hàng
    private Integer status; //Trạng thái đơn hàng: 0-Chưa xác nhận | 1-Đã xác nhận
    private List<OrderDetailDto> orderDetail; //Danh sách chi tiết đơn đặt hàng
    private String address; //Địa chỉ giao hàng

    public OrderDto() {
    }
}
