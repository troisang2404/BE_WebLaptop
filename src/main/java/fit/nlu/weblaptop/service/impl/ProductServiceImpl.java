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

    @Override
    public Optional<ProductEntity> findDetailById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Optional<ProductEntity> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Page<ProductEntity> findByCategoryId(BrandEntity id, Pageable pageable) {
        return productRepository.findByBrand(id, pageable);
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
    public Page<ProductEntity> findProductUnder10tr(Pageable pageable) {
        return productRepository.findByPriceUnder10tr(pageable);
    }

    @Override
    public Page<ProductEntity> findProductUnder20tr(Pageable pageable) {
        return productRepository.findByPriceUnder20tr(pageable);
    }

    @Override
    public Page<ProductEntity> findProductTop20Tr(Pageable pageable) {
        return productRepository.findByPriceTop20tr(pageable);
    }
}
