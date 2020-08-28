package com.example.service.impl;

import com.example.converter.EventConverter;
import com.example.dto.EventDTO;
import com.example.entity.EventEntity;
import com.example.entity.ProductEntity;
import com.example.repository.EventRepository;
import com.example.service.IEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class EventService implements IEventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private EventConverter eventConverter;

    @Override
    public List<EventDTO> findAll() {
        List<EventEntity> results = eventRepository.findAll();
        return results.stream().map(item -> eventConverter.convertToDto(item)).collect(Collectors.toList());
    }

    @Override
    public EventDTO findById(long id) {
        EventEntity eventEntity = eventRepository.findOne(id);
        EventDTO eventDTO = eventConverter.convertToDto(eventEntity);
        return eventDTO;
    }

    @Override
    @Transactional
    public EventDTO insert(EventDTO eventDTO) {
        EventEntity eventEntity = eventConverter.convertToEntity(eventDTO);
        return eventConverter.convertToDto(eventRepository.save(eventEntity));
    }

    @Override
    @Transactional
    public EventDTO update(EventDTO eventDTO) {
        EventEntity oldEvent = eventRepository.findOne(eventDTO.getId());
        oldEvent.setName(eventDTO.getName());
        oldEvent.setCode(eventDTO.getCode());
        oldEvent.setPercent(eventDTO.getPercent());
        return eventConverter.convertToDto(eventRepository.save(oldEvent));
    }

    @Override
    @Transactional
    public void deleteEvent(long[] ids) {
        for (Long item : ids) {
            eventRepository.delete(item);
        }
    }
}
