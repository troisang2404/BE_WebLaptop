package fit.nlu.weblaptop.repository;

import fit.nlu.weblaptop.entity.BrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BrandRepository extends JpaRepository<BrandEntity, Long> {

    List<BrandEntity> findByName(String name);

    @Query("select distinct b from BrandEntity b left join fetch b.product p")
    List<BrandEntity> findAllFetchEager();
}
