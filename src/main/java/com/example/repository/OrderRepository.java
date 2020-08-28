package com.example.repository;

import com.example.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    List<OrderEntity> findByUserOrder_UserName(String name);
    List<OrderEntity> findByCreatedDateBefore(Date to);
    List<OrderEntity> findByCreatedDateAfter(Date from);
    List<OrderEntity> findByCreatedDateBetween(Date from, Date to);
}
