package fit.nlu.weblaptop.service.impl;

import fit.nlu.weblaptop.entity.BrandEntity;
import fit.nlu.weblaptop.entity.ProductEntity;
import fit.nlu.weblaptop.repository.ProductRepository;
import fit.nlu.weblaptop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public ProductEntity save(ProductEntity product) {
        return productRepository.save(product);
    }

//    @Override
//    public Optional<ProductEntity> findOneById(Long id) {
//        return productRepository.findOneById(id);
//    }

    @Override
    public Optional<ProductEntity> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public List<ProductEntity> findByCategoryId(BrandEntity id) {
        return productRepository.findByBrand(id);
    }

    @Override
    public Page<ProductEntity> findAllPaging(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public List<ProductEntity> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<ProductEntity> findByProductName(String productName) {
        return productRepository.findByName(productName);
    }

    @Override
    public Page<ProductEntity> searchAutocomplete(String key, Pageable pageable) {
        return productRepository.findByNameIgnoreCaseContainingAndStatus(key, 1, pageable);
    }

    @Override
    public List<ProductEntity> findProductUnder10tr() {
        return productRepository.findByPriceUnder10tr();
    }

    @Override
    public List<ProductEntity> findProductUnder20tr() {
        return productRepository.findByPriceUnder20tr();
    }

    @Override
    public List<ProductEntity> findProductTop20Tr() {
        return productRepository.findByPriceTop20tr();
    }
}
