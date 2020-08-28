package com.example.api.admin;

import com.example.dto.BrandDTO;
import com.example.dto.FeatureDTO;
import com.example.service.IBrandService;
import com.example.service.IFeatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController(value = "brandApiOfAdmin")
@RequestMapping(value = "/api/admin/brand")
public class BrandAPI {

    @Autowired
    private IBrandService brandService;

    @PostMapping
    public ResponseEntity<BrandDTO> createBrand(@RequestBody BrandDTO brandDTO) {
        return ResponseEntity.ok(brandService.insert(brandDTO));
    }

    @PutMapping
    public ResponseEntity<BrandDTO> updateBrand(@RequestBody BrandDTO brandDTO) {
        return ResponseEntity.ok(brandService.update(brandDTO));
    }

    @DeleteMapping
    public ResponseEntity<String> deleteBrand(@RequestBody long[] ids) {
        if (ids.length > 0) {
            brandService.deleteBrand(ids);
        }
        return ResponseEntity.ok("success");
    }
}
