package fit.nlu.weblaptop.service;

import fit.nlu.weblaptop.entity.BrandEntity;
import fit.nlu.weblaptop.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    ProductEntity save(ProductEntity product);

//    Optional<ProductEntity> findOneById(Long id);

    Optional<ProductEntity> findById(Long id);

    List<ProductEntity> findByCategoryId(BrandEntity id);

    Page<ProductEntity> findAllPaging(Pageable pageable);

    List<ProductEntity> getAllProducts();

    Page<ProductEntity> searchAutocomplete(String key, Pageable pageable);

    List<ProductEntity> findProductUnder10tr();

    List<ProductEntity> findProductUnder20tr();

    List<ProductEntity> findProductTop20Tr();

    List<ProductEntity> findByProductName(String productName);
}
