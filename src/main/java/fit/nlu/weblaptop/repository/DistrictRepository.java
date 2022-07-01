package fit.nlu.weblaptop.repository;

import fit.nlu.weblaptop.entity.DistrictEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DistrictRepository extends JpaRepository<DistrictEntity, Long> {
}
