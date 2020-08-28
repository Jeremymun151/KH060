package com.example.service;

import com.example.dto.FeatureDetailDTO;
import com.example.dto.FeatureSearchDTO;

import java.util.List;
import java.util.Map;

public interface IFeatureDetailService {
    Map<String, String> getFeatures();
    List<FeatureSearchDTO> findAll();
    List<FeatureDetailDTO> findAllVersion2();
    FeatureDetailDTO findById(long id);
    FeatureDetailDTO insert(FeatureDetailDTO featureDetailDTO);
    FeatureDetailDTO update(FeatureDetailDTO featureDetailDTO);
    void deleteFeatureDetail(long[] ids);
    List<FeatureSearchDTO> findByProductCategory(String code);
}
