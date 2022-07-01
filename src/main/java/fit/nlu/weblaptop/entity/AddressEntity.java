package fit.nlu.weblaptop.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "address")
public class AddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String detail;

    @OneToOne
    private ProvinceEntity province;
    @OneToOne
    private DistrictEntity district;
    @OneToOne
    private VillageEntity village;

    @OneToOne
    private OrdersEntity orders;

}
