package fit.nlu.weblaptop.controller.web;

import fit.nlu.weblaptop.entity.BrandEntity;
import fit.nlu.weblaptop.entity.ProductEntity;
import fit.nlu.weblaptop.service.BrandService;
import fit.nlu.weblaptop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin(origins = "http://localhost:3000/")
public class ProductDetailController {
    @Autowired
    ProductService productService;

    @Autowired
    BrandService brandService;

//    @GetMapping("/detail/{id}")
//    public String show(Model model,
//                       @PathVariable(name = "id") Long id) {
//        ProductEntity productEntity = productService.findDetailById(id);
//        BrandEntity brandEntity = brandService.findOneById(productEntity.getBrand().getId());
//
//        Page<ProductEntity> page = productService.findByCategoryId(brandEntity, PageRequest.of(0, 5));
//        model.addAttribute("pagedetail", page);
//        model.addAttribute("detail", productEntity);
//        return "web/detail";
//    }

    @GetMapping("/detail")
    public String detail() {
        return "web/detail";
    }
}
