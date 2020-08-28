package com.example.api.admin;

import com.example.dto.FeatureDetailDTO;
import com.example.dto.UserDTO;
import com.example.service.IFeatureDetailService;
import com.example.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController(value = "featureDetailApiOfAdmin")
@RequestMapping(value = "/api/admin/featuredetail")
public class FeatureDetailAPI {

    @Autowired
    private IFeatureDetailService featureDetailService;

    @PostMapping
    public ResponseEntity<FeatureDetailDTO> createUser(@RequestBody FeatureDetailDTO featureDetailDTO) {
        return ResponseEntity.ok(featureDetailService.insert(featureDetailDTO));
    }

    @PutMapping
    public ResponseEntity<FeatureDetailDTO> updateUser(@RequestBody FeatureDetailDTO featureDetailDTO) {
        return ResponseEntity.ok(featureDetailService.update(featureDetailDTO));
    }

    @DeleteMapping
    public ResponseEntity<String> deleteProduct(@RequestBody long[] ids) {
        if (ids.length > 0) {
            featureDetailService.deleteFeatureDetail(ids);
        }
        return ResponseEntity.ok("success");
    }
}
