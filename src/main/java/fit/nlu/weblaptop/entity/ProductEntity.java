package fit.nlu.weblaptop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(Double originalPrice) {
        this.originalPrice = originalPrice;
    }

    public Double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Double salePrice) {
        this.salePrice = salePrice;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getImageLink1() {
        return imageLink1;
    }

    public void setImageLink1(String imageLink1) {
        this.imageLink1 = imageLink1;
    }

    public String getImageLink2() {
        return imageLink2;
    }

    public void setImageLink2(String imageLink2) {
        this.imageLink2 = imageLink2;
    }

    public String getImageLink3() {
        return imageLink3;
    }

    public void setImageLink3(String imageLink3) {
        this.imageLink3 = imageLink3;
    }

    public BrandEntity getBrand() {
        return brand;
    }

    public void setBrand(BrandEntity brand) {
        this.brand = brand;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getDisk() {
        return disk;
    }

    public void setDisk(String disk) {
        this.disk = disk;
    }

    public String getScreen() {
        return screen;
    }

    public void setScreen(String screen) {
        this.screen = screen;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getDesign() {
        return design;
    }

    public void setDesign(String design) {
        this.design = design;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public List<OrderDetailEntity> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderDetailEntity> orders) {
        this.orders = orders;
    }
}