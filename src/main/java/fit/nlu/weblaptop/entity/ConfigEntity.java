package fit.nlu.weblaptop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@Entity
@Table(name = "config")
public class ConfigEntity extends Auditable {
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

    /**
     * Khóa ngoại
     */
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnore
    private ProductEntity product;

}
