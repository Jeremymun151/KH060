package com.example.api.admin;

import com.example.dto.EventDTO;
import com.example.dto.UserDTO;
import com.example.service.IEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController(value = "eventApiOfAdmin")
@RequestMapping(value = "/api/admin/event")
public class EventAPI {

    @Autowired
    private IEventService eventService;

    @PostMapping
    public ResponseEntity<EventDTO> createUser(@RequestBody EventDTO eventDTO) {
        return ResponseEntity.ok(eventService.insert(eventDTO));
    }

    @PutMapping
    public ResponseEntity<EventDTO> updateUser(@RequestBody EventDTO eventDTO) {
        return ResponseEntity.ok(eventService.update(eventDTO));
    }

    @DeleteMapping
    public ResponseEntity<String> deleteProduct(@RequestBody long[] ids) {
        if (ids.length > 0) {
            eventService.deleteEvent(ids);
        }
        return ResponseEntity.ok("success");
    }
}
