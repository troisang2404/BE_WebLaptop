package fit.nlu.weblaptop.controller.web;

import fit.nlu.weblaptop.entity.DistrictEntity;
import fit.nlu.weblaptop.entity.ProvinceEntity;
import fit.nlu.weblaptop.repository.DistrictRepository;
import fit.nlu.weblaptop.repository.ProvinceRepository;
import fit.nlu.weblaptop.repository.VillageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins = "http://localhost:3000/")
@RestController
public class AddressController {
    @Autowired
    private ProvinceRepository provinceRepository;
    @Autowired
    private DistrictRepository districtRepository;
    @Autowired
    private VillageRepository villageRepository;

    @GetMapping("/province")
    public ResponseEntity<?> getProvince() {
        return ResponseEntity.ok(provinceRepository.findAll());
    }

    @GetMapping("/district/{id}")
    public ResponseEntity<?> getDistrict(@PathVariable("id") Long id) {
        ProvinceEntity province = provinceRepository.findOneById(id);
        return ResponseEntity.ok(districtRepository.findByProvince(province));
    }

    @GetMapping("/village/{id}")
    public ResponseEntity<?> getvillage(@PathVariable("id") Long id) {
        DistrictEntity district = districtRepository.findOneById(id);
        return ResponseEntity.ok(villageRepository.findByDistrict(district));
    }
}
