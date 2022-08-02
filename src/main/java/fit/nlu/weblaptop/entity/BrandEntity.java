package fit.nlu.weblaptop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "brand")
public class BrandEntity extends Auditable {
    private String name;        //Tên thương hiệu
    private String image;       //Hình ảnh thương hiệu
    private Integer priority;   //Thứ tự ưu tiên thương hiệu
    private Integer status;     //Trạng thái ẩn/hiện của thương hiệu

    /**
     * Khóa ngoại
     * CascadeType.ALL - Khi insert brand sẽ insert luôn product nếu product được khởi tạo
     */
    @OneToMany(mappedBy = "brand", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<ProductEntity> product;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<ProductEntity> getProduct() {
        return product;
    }

    public void setProduct(List<ProductEntity> product) {
        this.product = product;
    }
}
