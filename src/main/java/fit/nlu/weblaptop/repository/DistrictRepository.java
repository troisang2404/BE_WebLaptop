package fit.nlu.weblaptop.repository;

import fit.nlu.weblaptop.entity.DistrictEntity;
import fit.nlu.weblaptop.entity.ProvinceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DistrictRepository extends JpaRepository<DistrictEntity, Long> {
    List<DistrictEntity> findByProvince(ProvinceEntity province);
    DistrictEntity findOneById(Long id);

}
