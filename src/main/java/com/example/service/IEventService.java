package com.example.service;

import com.example.dto.EventDTO;

import java.util.List;

public interface IEventService {
    List<EventDTO> findAll();
    EventDTO findById(long id);
    EventDTO insert(EventDTO userDTO);
    EventDTO update(EventDTO userDTO);
    void deleteEvent(long[] ids);
}
