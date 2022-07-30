package fit.nlu.weblaptop.service;

import fit.nlu.weblaptop.entity.BrandEntity;

import java.util.List;
import java.util.Optional;

public interface BrandService {
    Optional<BrandEntity> findById(Long id);

    BrandEntity findOneById(Long id);

    List<BrandEntity> findAllFetchEager();

    List<BrandEntity> findByBrandName(String brandName);

    BrandEntity save(BrandEntity brand);

    void deleteById(Long id);

    boolean existsById(Long id);

}
