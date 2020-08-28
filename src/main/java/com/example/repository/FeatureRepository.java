package com.example.repository;

import com.example.entity.FeatureEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeatureRepository extends JpaRepository<FeatureEntity, Long> {
    FeatureEntity findOneByCode(String code);
    List<FeatureEntity> findByProductCategoryCode(String code);
}
