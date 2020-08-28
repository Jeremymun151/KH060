package com.example.service.impl;

import com.example.converter.ProductCategoryConverter;
import com.example.dto.ProductCategoryDTO;
import com.example.entity.ProductCategoryEntity;
import com.example.repository.ProductCategoryRepository;
import com.example.service.IProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProductCategoryService implements IProductCategoryService {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Autowired
    private ProductCategoryConverter productCategoryConverter;

    @Override
    public Map<String, String> getCategories() {
        Map<String, String> results = new HashMap<>();
        productCategoryRepository.findAll().forEach(item -> results.put(item.getCode(), item.getName()));
        return results;
    }

    @Override
    public List<ProductCategoryDTO> findAll() {
        List<ProductCategoryEntity> productCategoryEntities = productCategoryRepository.findAll();
        return productCategoryEntities.stream().map(item -> productCategoryConverter.convertToDto(item)).collect(Collectors.toList());
    }

    @Override
    public ProductCategoryDTO findById(long id) {
        ProductCategoryEntity productCategoryEntity = productCategoryRepository.findOne(id);
        ProductCategoryDTO productCategoryDTO = productCategoryConverter.convertToDto(productCategoryEntity);
        return productCategoryDTO;
    }

    @Override
    @Transactional
    public ProductCategoryDTO insert(ProductCategoryDTO productCategoryDTO) {
        ProductCategoryEntity productCategoryEntity = productCategoryConverter.convertToEntity(productCategoryDTO);
        return productCategoryConverter.convertToDto(productCategoryRepository.save(productCategoryEntity));
    }

    @Override
    @Transactional
    public ProductCategoryDTO update(ProductCategoryDTO productCategoryDTO) {
        ProductCategoryEntity old = productCategoryRepository.findOne(productCategoryDTO.getId());
        old.setName(productCategoryDTO.getName());
        old.setCode(productCategoryDTO.getCode());
        return productCategoryConverter.convertToDto(productCategoryRepository.save(old));
    }

    @Override
    @Transactional
    public void deleteProductCategory(long[] ids) {
        for (Long item : ids) {
            productCategoryRepository.delete(item);
        }
    }
}
