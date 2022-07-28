package fit.nlu.weblaptop.controller.admin;

import fit.nlu.weblaptop.entity.BrandEntity;
import fit.nlu.weblaptop.dto.response.ResponseObject;
import fit.nlu.weblaptop.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/admin/brand")
public class BrandController {
    @Autowired
    private BrandService brandService;

    /**
     * Hiển thị danh sách tất cả các thương hiệu
     */
    @GetMapping("/list")
    public ResponseEntity<List<BrandEntity>> getAllBrands() {
        return ResponseEntity.ok(brandService.findAllFetchEager());
    }

    /**
     * Hiển thị 1 thương hiệu
     */
    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> getBrand(@PathVariable(value = "id", required = false) Long id) {
        Optional<BrandEntity> foundBrand = brandService.findById(id);
        return foundBrand.isPresent() ?
                ResponseEntity.ok(new ResponseObject("ok", "", foundBrand)) :
                ResponseEntity.ok(new ResponseObject("failed", "Cannot find product with id = " + id, ""));
    }


    /**
     * Thêm thương hiệu
     */
    @PostMapping("/insert")
    public ResponseEntity<ResponseObject> insertBrand(@RequestBody BrandEntity newBrand) {
        //2 brand must not have the same name !
        List<BrandEntity> foundBrands = brandService.findByBrandName(newBrand.getName().trim());
        if (foundBrands.size() > 0) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new ResponseObject("failed", "Brand name already taken", "")
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Insert brand successfully", brandService.save(newBrand))
        );
    }

    /**
     * Cập nhật thương hiệu
     *
     * @param id thương hiệu
     */
    @PutMapping("/{id}")
    public ResponseEntity<ResponseObject> updateBrand(@RequestBody BrandEntity newBrand, @PathVariable Long id) {
        BrandEntity updateBrand = brandService.findById(id)
                .map(brand -> {
                    brand.setName(newBrand.getName());
                    brand.setImage(newBrand.getImage());
                    brand.setPriority(newBrand.getPriority());
                    brand.setStatus(newBrand.getStatus());
                    return brandService.save(brand);
                }).orElseGet(() -> {
                    newBrand.setId(id);
                    return brandService.save(newBrand);
                });
        return ResponseEntity.ok(
                new ResponseObject("ok", "Update brand successfully", updateBrand));
    }

    /**
     * Xóa thương hiêu
     *
     * @param id thương hiệu
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseObject> deleteBrand(@PathVariable Long id) {
        boolean exists = brandService.existsById(id);
        if(exists) {
            brandService.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Delete brand successfully", "")
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("failed", "Cannot find brand to delete", "")
        );
    }
}
