package fit.nlu.weblaptop.controller.web;

import fit.nlu.weblaptop.entity.ProductEntity;
import fit.nlu.weblaptop.payload.response.ResponseObject;
import fit.nlu.weblaptop.service.BrandService;
import fit.nlu.weblaptop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

//    @GetMapping("/detail/{id}")
//    public ResponseEntity<List<ProductEntity>> showDetail(@PathVariable(name = "id") Long id) {
//        Optional<ProductEntity> productEntity = productService.findOneById(id);
//        BrandEntity brandEntity = brandService.findOneById(productEntity.get().getBrand().getId());
//        return ResponseEntity.ok(productService.findByCategoryId(brandEntity));
//    }

    /**
     * Hiển thị danh sách sản phẩm
     */
    @GetMapping("/products")
    public ResponseEntity<List<ProductEntity>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    /**
     * Hiển thị 1 sản phẩm
     */
    @GetMapping(value = {"/detail/{id}"})
    public ResponseEntity getProduct(@PathVariable(value = "id", required = false) Long id) {
        Optional<ProductEntity> foundProduct = productService.findById(id);
        return foundProduct.isPresent() ?
                ResponseEntity.ok(foundProduct) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject("failed", "Cannot find product with id = " + id, "")
                );
    }

}
