package com.example.converter;

import com.example.dto.BrandDTO;
import com.example.dto.ProductCategoryDTO;
import com.example.entity.BrandEntity;
import com.example.entity.ProductCategoryEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BrandConverter {

    @Autowired
    private ModelMapper modelMapper;

    public BrandDTO convertToDto(BrandEntity entity) {
        BrandDTO result = modelMapper.map(entity, BrandDTO.class);
        return result;
    }

    public BrandEntity convertToEntity(BrandDTO dto) {
        BrandEntity result = modelMapper.map(dto, BrandEntity.class);
        return result;
    }
}
