package com.example.service.impl;

import com.example.converter.BrandConverter;
import com.example.dto.BrandDTO;
import com.example.entity.BrandEntity;
import com.example.repository.BrandRepository;
import com.example.service.IBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BrandService implements IBrandService {

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private BrandConverter brandConverter;

    @Override
    public Map<String, String> getBrands() {
        Map<String, String> results = new HashMap<>();
        brandRepository.findAll().forEach(item -> results.put(item.getCode(), item.getName()));
        return results;
    }

    @Override
    public List<BrandDTO> findAll() {
        List<BrandEntity> brandEntities = brandRepository.findAll();
        return brandEntities.stream().map(item -> brandConverter.convertToDto(item)).collect(Collectors.toList());
    }

    @Override
    public BrandDTO findById(long id) {
        BrandEntity brandEntity = brandRepository.findOne(id);
        BrandDTO brandDTO = brandConverter.convertToDto(brandEntity);
        return brandDTO;
    }

    @Override
    @Transactional
    public BrandDTO insert(BrandDTO brandDTO) {
        BrandEntity brandEntity = brandConverter.convertToEntity(brandDTO);
        return brandConverter.convertToDto(brandRepository.save(brandEntity));
    }

    @Override
    @Transactional
    public BrandDTO update(BrandDTO brandDTO) {
        BrandEntity old = brandRepository.findOne(brandDTO.getId());
        old.setName(brandDTO.getName());
        old.setCode(brandDTO.getCode());
        old.setProductCategoryCode(brandDTO.getProductCategoryCode());
        return brandConverter.convertToDto(brandRepository.save(old));
    }

    @Override
    @Transactional
    public void deleteBrand(long[] ids) {
        for (Long item : ids) {
            brandRepository.delete(item);
        }
    }

    @Override
    public List<BrandDTO> findByProductCategory(String code) {
        List<BrandEntity> brandEntities = brandRepository.findByProductCategoryCode(code);
        return brandEntities.stream().map(item -> brandConverter.convertToDto(item)).collect(Collectors.toList());
    }
}
