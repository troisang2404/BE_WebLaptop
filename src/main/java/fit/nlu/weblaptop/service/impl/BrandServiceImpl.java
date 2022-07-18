package fit.nlu.weblaptop.service.impl;

import fit.nlu.weblaptop.entity.BrandEntity;
import fit.nlu.weblaptop.repository.BrandRepository;
import fit.nlu.weblaptop.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandRepository brandRepository;

    @Override
    public Optional<BrandEntity> findById(Long id) {
        return brandRepository.findById(id);
    }

    @Override
    public BrandEntity findOneById(Long id) {
        return brandRepository.findOneById(id);
    }

    @Override
    public List<BrandEntity> findByBrandName(String brandName) {
        return brandRepository.findByName(brandName);
    }

    @Override
    public List<BrandEntity> findAllFetchEager() {
        return brandRepository.findAllFetchEager();
    }

    @Override
    public BrandEntity save(BrandEntity brand) {
        return brandRepository.save(brand);
    }

    @Override
    public void deleteById(Long id) {
        brandRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return brandRepository.existsById(id);
    }


    @Override
    public List<BrandEntity> findAll() {
        return brandRepository.findAll();
    }


}
