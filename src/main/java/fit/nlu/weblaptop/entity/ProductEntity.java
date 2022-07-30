package fit.nlu.weblaptop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@Entity
@Table(name = "product")
public class ProductEntity extends Auditable {

    private String name;
    //    @JsonIgnore
    @Column(columnDefinition = "TEXT") // Định nhĩa kiểu dữ liệu cho 'description' = TEXT
    private String description;     //Mô tả

    @NumberFormat(pattern = "#")
    @Column(name = "original_price", nullable = false)
    private Double originalPrice;   //Giá gốc

    @NumberFormat(pattern = "#")
    @Column(name = "sale_price", nullable = false)
    private Double salePrice;       //Giá bán

    @NumberFormat(pattern = "#")
    private Integer stock;          //Số lượng sản phẩm trong kho
    private Integer status;         //Trạng thái của sản phẩm

    //link ảnh
    private String imageLink1;
    private String imageLink2;
    private String imageLink3;
    /**
     * Khóa ngoại
     * CascadeType.ALL - Khi insert product sẽ insert luôn image
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn
//    @JsonIgnore
    private BrandEntity brand;//Một sản phẩm chỉ thuộc một danh mục hàng hóa

    //Cấu hình sản phẩm
    private String cpu;             //CPU
    private String ram;             //RAM
    private String disk;            //Ổ cứng
    private String screen;          //Màn hình
    private String card;            //Card đồ họa
    private String port;            //Cân nặng
    private String os;              //Hệ điều hành
    private String design;          //Thiết kế
    private String size;            //Kích thước
    private String year;            //Năm ra mắt

    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private List<OrderDetailEntity> orders; //Danh sách chi tiết đơn đặt hàng

}