package com.example.repository;

import com.example.entity.BrandEntity;
import com.example.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BrandRepository extends JpaRepository<BrandEntity, Long> {
    List<BrandEntity> findByProductCategoryCode(String code);
    BrandEntity findOneByCode(String code);
}
