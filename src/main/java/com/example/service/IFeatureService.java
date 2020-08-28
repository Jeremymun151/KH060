package com.example.service;

import com.example.dto.FeatureDTO;

import java.util.List;
import java.util.Map;

public interface IFeatureService {
    Map<String, String> getFeatures();
    List<FeatureDTO> findAll();
    FeatureDTO findById(long id);
    FeatureDTO insert(FeatureDTO featureDTO);
    FeatureDTO update(FeatureDTO featureDTO);
    void deleteFeature(long[] ids);
}
