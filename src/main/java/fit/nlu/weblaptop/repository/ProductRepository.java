package fit.nlu.weblaptop.repository;

import fit.nlu.weblaptop.entity.BrandEntity;
import fit.nlu.weblaptop.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    List<ProductEntity> findByName(String name);

    Page<ProductEntity> findByBrand(BrandEntity brandEntity, Pageable pageable);

    Page<ProductEntity> findByNameIgnoreCaseContainingAndStatus(String name, Integer status, Pageable pageable);

    @Query(value = "select p from ProductEntity p where p.salePrice between 5000000 and 10000000")
    Page<ProductEntity> findByPriceUnder10tr(Pageable pageable);

    @Query(value = "select p from ProductEntity p where p.salePrice between 10000000 and 20000000")
    Page<ProductEntity> findByPriceUnder20tr(Pageable pageable);

    @Query(value = "select p from ProductEntity p where p.salePrice > 20000000")
    Page<ProductEntity> findByPriceTop20tr(Pageable pageable);

}
