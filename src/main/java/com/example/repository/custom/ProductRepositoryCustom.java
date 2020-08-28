package com.example.repository.custom;

import com.example.dto.HomeSearchDTO;
import com.example.entity.ProductEntity;

import java.util.List;

public interface ProductRepositoryCustom {
    List<ProductEntity> findAll(HomeSearchDTO homeSearchDTO);
}
