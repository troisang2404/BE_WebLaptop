package fit.nlu.weblaptop.controller.admin;

import fit.nlu.weblaptop.entity.ProductEntity;
import fit.nlu.weblaptop.dto.response.ResponseObject;
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
                        new ResponseObject("failed", "Không tìm thấy sản phẩm với id = " + id, "")
                );
    }

    /**
     * Thêm sản phẩm
     */
    @PostMapping("/insert")
    public ResponseEntity<ResponseObject> insertProduct(@RequestBody ProductEntity newProduct) {
        //2 product must not have the same name !
        List<ProductEntity> foundProducts = productService.findByProductName(newProduct.getName().trim());
        if (foundProducts.size() > 0) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new ResponseObject("failed", "Tên sản phẩm đã được sử dụng", "")
            );
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ResponseObject("ok", "Thêm sản phẩm thành công", productService.save(newProduct))
        );
    }

    /**
     * Cập nhật sản phẩm
     *
     * @param id thương hiệu
     */
    @PutMapping("/{id}")
    public ResponseEntity<ResponseObject> updateProduct(@RequestBody ProductEntity newProduct, @PathVariable Long id) {
        ProductEntity updateProduct = productService.findById(id)
                .map(product -> {
                    product.setName(newProduct.getName());
                    product.setDescription(newProduct.getDescription());
                    product.setOriginalPrice(newProduct.getOriginalPrice());
                    product.setSalePrice(newProduct.getSalePrice());
                    product.setStatus(newProduct.getStatus());
                    product.setStock(newProduct.getStock());
                    product.setBrand(newProduct.getBrand());
                    product.setImageLink1(newProduct.getImageLink1());
                    product.setImageLink2(newProduct.getImageLink2());
                    product.setImageLink3(newProduct.getImageLink3());
                    product.setCard(newProduct.getCard());
                    product.setCpu(newProduct.getCpu());
                    product.setDesign(newProduct.getDesign());
                    product.setDisk(newProduct.getDisk());
                    product.setOs(newProduct.getOs());
                    product.setPort(newProduct.getPort());
                    product.setRam(newProduct.getRam());
                    product.setScreen(newProduct.getScreen());
                    product.setSize(newProduct.getSize());
                    product.setYear(newProduct.getYear());
                    return productService.save(product);
                }).orElseGet(() -> {
                    newProduct.setId(id);
                    return productService.save(newProduct);
                });
        return ResponseEntity.ok(
                new ResponseObject("ok", "Cập nhật sản phẩm thành công", updateProduct));
    }

    /**
     * Xóa sản phẩm
     *
     * @param id sản phẩm
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseObject> deleteProduct(@PathVariable Long id) {
        boolean exists = productService.existsById(id);
        if (exists) {
            productService.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Xoá sản phẩm thành công", "")
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("failed", "Không thể tìm thấy sản phẩm để xóa", "")
        );
    }
}
