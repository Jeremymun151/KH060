package com.example.service;

import com.example.dto.OrderDTO;

import java.util.List;

public interface IOrderService {
    List<OrderDTO> findAll();
    List<OrderDTO> findByUser(String userName);
    void updateStatus(OrderDTO orderDTO);
    List<OrderDTO> report(String fromDate, String toDate);
}
