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

    ProductEntity findOneById(Long id);

    List<ProductEntity> findByBrand(BrandEntity brandEntity);

    Page<ProductEntity> findByNameIgnoreCaseContainingAndStatus(String name, Integer status, Pageable pageable);

    @Query(value = "select p from ProductEntity p where p.salePrice between 5000000 and 10000000")
    List<ProductEntity> findByPriceUnder10tr();

    @Query(value = "select p from ProductEntity p where p.salePrice between 10000000 and 20000000")
    List<ProductEntity> findByPriceUnder20tr();

    @Query(value = "select p from ProductEntity p where p.salePrice > 20000000")
    List<ProductEntity> findByPriceTop20tr();

}
