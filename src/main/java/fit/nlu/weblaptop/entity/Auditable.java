package fit.nlu.weblaptop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @CreatedDate
    @Column(name = "created_date")
    private Date createdDate;

    @JsonIgnore
    @LastModifiedDate
    @Column(name = "modified_date")
    private Date modifiedDate;

    @JsonIgnore
    @CreatedBy
    @Column(name = "created_by")
    private String createdBy;

    @JsonIgnore
    @LastModifiedBy
    @Column(name = "modified_by")
    private String modifiedBy;

}
