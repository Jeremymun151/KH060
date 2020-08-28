package com.example.service.impl;

import com.example.converter.FeatureDetailConverter;
import com.example.dto.FeatureDetailDTO;
import com.example.dto.FeatureSearchDTO;
import com.example.entity.FeatureDetailEntity;
import com.example.entity.FeatureEntity;
import com.example.repository.FeatureDetailRepository;
import com.example.repository.FeatureRepository;
import com.example.service.IFeatureDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class FeatureDetailService implements IFeatureDetailService {

    @Autowired
    private FeatureDetailRepository featureDetailRepository;

    @Autowired
    private FeatureRepository featureRepository;

    @Autowired
    private FeatureDetailConverter featureDetailConverter;

    @Override
    public Map<String, String> getFeatures() {
        Map<String, String> results = new HashMap<>();
        for (FeatureDetailEntity item: featureDetailRepository.findAll()) {
            results.put(item.getCode(), item.getName());
        }
        return results;
    }

    @Override
    public List<FeatureSearchDTO> findAll() {
        List<FeatureSearchDTO> results = new ArrayList<>();
        List<FeatureEntity> featureEntities = featureRepository.findAll();
        for (FeatureEntity item: featureEntities) {
            FeatureSearchDTO featureSearchDTO = new FeatureSearchDTO();
            featureSearchDTO.setName(item.getName());
            Map<String, String> maps = new HashMap<>();
            for (FeatureDetailEntity featureDetail: item.getFeatureDetails()) {
                maps.put(featureDetail.getCode(), featureDetail.getName());
            }
            featureSearchDTO.setFeatures(maps);
            results.add(featureSearchDTO);
        }
        return results;
    }

    @Override
    public List<FeatureDetailDTO> findAllVersion2() {
        List<FeatureDetailEntity> results = featureDetailRepository.findAll();
        return results.stream().map(item -> featureDetailConverter.convertToDto(item)).collect(Collectors.toList());
    }

    @Override
    public FeatureDetailDTO findById(long id) {
        FeatureDetailEntity featureDetailEntity = featureDetailRepository.findOne(id);
        FeatureDetailDTO featureDetailDTO = featureDetailConverter.convertToDto(featureDetailEntity);
        featureDetailDTO.setFeatureCode(featureDetailEntity.getFeature().getCode());
        return featureDetailDTO;
    }

    @Override
    @Transactional
    public FeatureDetailDTO insert(FeatureDetailDTO featureDetailDTO) {
        FeatureEntity feature = featureRepository.findOneByCode(featureDetailDTO.getFeatureCode());
        FeatureDetailEntity featureDetailEntity = featureDetailConverter.convertToEntity(featureDetailDTO);
        featureDetailEntity.setFeature(feature);
        return featureDetailConverter.convertToDto(featureDetailRepository.save(featureDetailEntity));
    }

    @Override
    @Transactional
    public FeatureDetailDTO update(FeatureDetailDTO featureDetailDTO) {
        FeatureEntity feature = featureRepository.findOneByCode(featureDetailDTO.getFeatureCode());
        FeatureDetailEntity old = featureDetailRepository.findOne(featureDetailDTO.getId());
        old.setName(featureDetailDTO.getName());
        old.setCode(featureDetailDTO.getCode());
        old.setFeature(feature);
        return featureDetailConverter.convertToDto(featureDetailRepository.save(old));
    }

    @Override
    @Transactional
    public void deleteFeatureDetail(long[] ids) {
        for (Long item : ids) {
            featureDetailRepository.delete(item);
        }
    }

    @Override
    public List<FeatureSearchDTO> findByProductCategory(String code) {
        List<FeatureSearchDTO> results = new ArrayList<>();
        List<FeatureEntity> featureEntities = featureRepository.findByProductCategoryCode(code);
        for (FeatureEntity item: featureEntities) {
            FeatureSearchDTO featureSearchDTO = new FeatureSearchDTO();
            featureSearchDTO.setName(item.getName());
            Map<String, String> maps = new HashMap<>();
            for (FeatureDetailEntity featureDetail: item.getFeatureDetails()) {
                maps.put(featureDetail.getCode(), featureDetail.getName());
            }
            featureSearchDTO.setFeatures(maps);
            results.add(featureSearchDTO);
        }
        return results;
    }


}
