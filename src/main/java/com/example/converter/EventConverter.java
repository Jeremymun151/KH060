package com.example.converter;

import com.example.dto.EventDTO;
import com.example.dto.UserDTO;
import com.example.entity.EventEntity;
import com.example.entity.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EventConverter {

    @Autowired
    private ModelMapper modelMapper;

    public EventDTO convertToDto (EventEntity entity){
        EventDTO result = modelMapper.map(entity, EventDTO.class);
        return result;
    }

    public EventEntity convertToEntity (EventDTO dto){
        EventEntity result = modelMapper.map(dto, EventEntity.class);
        return result;
    }
}
