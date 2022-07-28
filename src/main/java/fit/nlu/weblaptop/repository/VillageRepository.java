package fit.nlu.weblaptop.repository;

import fit.nlu.weblaptop.entity.VillageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VillageRepository extends JpaRepository<VillageEntity, Long> {
    VillageEntity findOneById(Long id);
}
