package fit.nlu.weblaptop.controller.admin;

import fit.nlu.weblaptop.entity.ProductEntity;
import fit.nlu.weblaptop.payload.response.ResponseObject;
import fit.nlu.weblaptop.service.BrandService;
import fit.nlu.weblaptop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/admin/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private BrandService brandService;

    /**
     * Hiển thị danh sách sản phẩm theo phân trang
     */
    @GetMapping("/list")
    public ResponseEntity<Page<ProductEntity>> showPage(@RequestParam(name = "page", required = false, defaultValue = "0") Integer page) {
        return new ResponseEntity<>(productService.findAllPaging(PageRequest.of(page, 20)), HttpStatus.OK);
    }

    /**
     * Hiển thị danh sách sản phẩm
     */
    @GetMapping("/lists")
    public ResponseEntity<List<ProductEntity>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    /**
     * Hiển thị 1 sản phẩm
     */
    @GetMapping(value = {"/{id}"})
    public ResponseEntity getProduct(@PathVariable(value = "id", required = false) Long id) {
        Optional<ProductEntity> foundProduct = productService.findById(id);
        return foundProduct.isPresent() ?
                ResponseEntity.ok(foundProduct) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject("failed", "Cannot find product with id = " + id, "")
                );
    }

    /**
     * Lưu sản phẩm
     */
    @PostMapping("/insert")
    public ResponseEntity<ResponseObject> insertBrand(@RequestBody ProductEntity newProduct) {
        //2 product must not have the same name !
        List<ProductEntity> foundProducts = productService.findByProductName(newProduct.getName().trim());
        if (foundProducts.size() > 0) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new ResponseObject("failed", "Product name already taken", "")
            );
        }
        newProduct.getImage().forEach(image -> image.setProduct(newProduct)); //Set product cho image
        newProduct.getConfig().setProduct(newProduct);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ResponseObject("ok", "Insert product successfully", productService.save(newProduct))
        );
    }
}
