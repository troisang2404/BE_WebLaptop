package fit.nlu.weblaptop.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "xa")
public class VillageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String location;
    private String type;

    @OneToOne
    @JoinColumn(name = "huyen_id")
    private DistrictEntity district;
}
