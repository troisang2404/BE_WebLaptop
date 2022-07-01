package fit.nlu.weblaptop.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@Entity
@Table(name = "comment")
public class CommentEntity extends Auditable {

    private Integer star;   //Số sao đánh giá
    private String content; //Nội dung đánh giá

    /**
     * Khóa ngoại
     */

    @ManyToOne(fetch = FetchType.LAZY)
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    private ProductEntity product;
}
