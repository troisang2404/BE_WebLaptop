package fit.nlu.weblaptop.controller.web;

import fit.nlu.weblaptop.entity.BrandEntity;
import fit.nlu.weblaptop.service.BrandService;
import fit.nlu.weblaptop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    ProductService productService;

    @Autowired
    BrandService brandService;

    @GetMapping(value = "/{id}")
    public ResponseEntity showIdCategory(@PathVariable(value = "id") Long id) {
        BrandEntity entity = brandService.findOneById(id);
        return ResponseEntity.ok(productService.findByCategoryId(entity));
    }

    @GetMapping("/tu5den10trieu")
    public ResponseEntity priceUnder10tr() {
        return ResponseEntity.ok(productService.findProductUnder10tr());
    }

    @GetMapping(value = "/tu10den20trieu")
    public ResponseEntity priceUnder20tr() {
        return ResponseEntity.ok(productService.findProductUnder20tr());
    }

    @GetMapping(value = "/tren20trieu")
    public ResponseEntity priceTop20tr() {
        return ResponseEntity.ok(productService.findProductTop20Tr());
    }
}
