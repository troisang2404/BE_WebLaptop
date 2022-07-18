package fit.nlu.weblaptop.controller.web;

import fit.nlu.weblaptop.entity.BrandEntity;
import fit.nlu.weblaptop.entity.ProductEntity;
import fit.nlu.weblaptop.service.BrandService;
import fit.nlu.weblaptop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;


@RestController
@CrossOrigin(origins = "http://localhost:3000/")
public class ProductDetailController {
    @Autowired
    ProductService productService;

    @Autowired
    BrandService brandService;

    @GetMapping("/detail/{id}")
    public ResponseEntity<List<ProductEntity>> showDetail(@PathVariable(name = "id") Long id) {
        Optional<ProductEntity> productEntity = productService.findOneById(id);
        BrandEntity brandEntity = brandService.findOneById(productEntity.get().getBrand().getId());
        return ResponseEntity.ok(productService.findByCategoryId(brandEntity));
    }

    @GetMapping("/detail")
    public String detail() {
        return "web/detail";
    }
}
