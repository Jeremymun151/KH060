package com.example.api.admin;

import com.example.dto.FeatureDTO;
import com.example.dto.ProductCategoryDTO;
import com.example.service.IFeatureService;
import com.example.service.IProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController(value = "featureApiOfAdmin")
@RequestMapping(value = "/api/admin/feature")
public class FeatureAPI {

    @Autowired
    private IFeatureService featureService;

    @PostMapping
    public ResponseEntity<FeatureDTO> createUser(@RequestBody FeatureDTO featureDTO) {
        return ResponseEntity.ok(featureService.insert(featureDTO));
    }

    @PutMapping
    public ResponseEntity<FeatureDTO> updateUser(@RequestBody FeatureDTO featureDTO) {
        return ResponseEntity.ok(featureService.update(featureDTO));
    }

    @DeleteMapping
    public ResponseEntity<String> deleteProduct(@RequestBody long[] ids) {
        if (ids.length > 0) {
            featureService.deleteFeature(ids);
        }
        return ResponseEntity.ok("success");
    }
}
