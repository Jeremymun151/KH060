package com.example.converter;

import com.example.dto.FeatureDTO;
import com.example.dto.UserDTO;
import com.example.entity.FeatureEntity;
import com.example.entity.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FeatureConverter {

    @Autowired
    private ModelMapper modelMapper;

    public FeatureDTO convertToDto (FeatureEntity entity){
        FeatureDTO result = modelMapper.map(entity, FeatureDTO.class);
        return result;
    }

    public FeatureEntity convertToEntity (FeatureDTO dto){
        FeatureEntity result = modelMapper.map(dto, FeatureEntity.class);
        return result;
    }
}
