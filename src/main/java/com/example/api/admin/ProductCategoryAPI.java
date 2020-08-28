package com.example.api.admin;

import com.example.dto.EventDTO;
import com.example.dto.ProductCategoryDTO;
import com.example.service.IEventService;
import com.example.service.IProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController(value = "productCategoryApiOfAdmin")
@RequestMapping(value = "/api/admin/productcategory")
public class ProductCategoryAPI {

    @Autowired
    private IProductCategoryService productCategoryService;

    @PostMapping
    public ResponseEntity<ProductCategoryDTO> createUser(@RequestBody ProductCategoryDTO productCategoryDTO) {
        return ResponseEntity.ok(productCategoryService.insert(productCategoryDTO));
    }

    @PutMapping
    public ResponseEntity<ProductCategoryDTO> updateUser(@RequestBody ProductCategoryDTO productCategoryDTO) {
        return ResponseEntity.ok(productCategoryService.update(productCategoryDTO));
    }

    @DeleteMapping
    public ResponseEntity<String> deleteProduct(@RequestBody long[] ids) {
        if (ids.length > 0) {
            productCategoryService.deleteProductCategory(ids);
        }
        return ResponseEntity.ok("success");
    }
}
