package com.example.repository;

import com.example.entity.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<EventEntity, Long> {
    EventEntity findOneByCode(String code);
}
