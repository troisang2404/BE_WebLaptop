package fit.nlu.weblaptop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
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
}
