package fit.nlu.weblaptop.service;

import fit.nlu.weblaptop.entity.BrandEntity;
import fit.nlu.weblaptop.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    ProductEntity save(ProductEntity product);

    Optional<ProductEntity> findDetailById(Long id);

    Optional<ProductEntity> findById(Long id);

    Page<ProductEntity> findByCategoryId(BrandEntity id, Pageable pageable);

    Page<ProductEntity> findAllPaging(Pageable pageable);

    List<ProductEntity> getAllProducts();

    Page<ProductEntity> searchAutocomplete(String key, Pageable pageable);

    Page<ProductEntity> findProductUnder10tr(Pageable pageable);

    Page<ProductEntity> findProductUnder20tr(Pageable pageable);

    Page<ProductEntity> findProductTop20Tr(Pageable pageable);

    List<ProductEntity> findByProductName(String productName);
}
