package com.example.service.impl;

import com.example.converter.FeatureConverter;
import com.example.dto.FeatureDTO;
import com.example.entity.FeatureEntity;
import com.example.repository.FeatureRepository;
import com.example.service.IFeatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FeatureService implements IFeatureService {

    @Autowired
    private FeatureRepository featureRepository;

    @Autowired
    private FeatureConverter featureConverter;

    @Override
    public Map<String, String> getFeatures() {
        Map<String, String> results = new HashMap<>();
        featureRepository.findAll().forEach(item -> results.put(item.getCode(), item.getName()));
        return results;
    }

    @Override
    public List<FeatureDTO> findAll() {
        List<FeatureEntity> productCategoryEntities = featureRepository.findAll();
        return productCategoryEntities.stream().map(item -> featureConverter.convertToDto(item)).collect(Collectors.toList());
    }

    @Override
    public FeatureDTO findById(long id) {
        FeatureEntity featureEntity = featureRepository.findOne(id);
        FeatureDTO productCategoryDTO = featureConverter.convertToDto(featureEntity);
        return productCategoryDTO;
    }

    @Override
    @Transactional
    public FeatureDTO insert(FeatureDTO featureDTO) {
        FeatureEntity featureEntity = featureConverter.convertToEntity(featureDTO);
        return featureConverter.convertToDto(featureRepository.save(featureEntity));
    }

    @Override
    @Transactional
    public FeatureDTO update(FeatureDTO featureDTO) {
        FeatureEntity old = featureRepository.findOne(featureDTO.getId());
        old.setName(featureDTO.getName());
        old.setCode(featureDTO.getCode());
        old.setProductCategoryCode(featureDTO.getProductCategoryCode());
        return featureConverter.convertToDto(featureRepository.save(old));
    }

    @Override
    @Transactional
    public void deleteFeature(long[] ids) {
        for (Long item : ids) {
            featureRepository.delete(item);
        }
    }
}
