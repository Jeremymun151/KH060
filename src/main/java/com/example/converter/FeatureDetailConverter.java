package com.example.converter;

import com.example.dto.FeatureDetailDTO;
import com.example.dto.UserDTO;
import com.example.entity.FeatureDetailEntity;
import com.example.entity.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FeatureDetailConverter {

    @Autowired
    private ModelMapper modelMapper;

    public FeatureDetailDTO convertToDto (FeatureDetailEntity entity){
        FeatureDetailDTO result = modelMapper.map(entity, FeatureDetailDTO.class);
        return result;
    }

    public FeatureDetailEntity convertToEntity (FeatureDetailDTO dto){
        FeatureDetailEntity result = modelMapper.map(dto, FeatureDetailEntity.class);
        return result;
    }
}
