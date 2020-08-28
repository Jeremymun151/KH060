package com.example.service.impl;

import com.example.dto.OrderDTO;
import com.example.entity.OrderEntity;
import com.example.entity.ProductEntity;
import com.example.repository.OrderRepository;
import com.example.repository.ProductRepository;
import com.example.service.IOrderService;
import com.example.util.DateUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderService implements IOrderService {


    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<OrderDTO> findAll() {
        List<OrderDTO> results = new ArrayList<>();
        List<OrderEntity> orderEntities = orderRepository.findAll();
        for (OrderEntity item: orderEntities) {
            OrderDTO orderDTO = new OrderDTO();
            orderDTO.setId(item.getId());
            orderDTO.setFullName(item.getUserOrder().getFullName());
            orderDTO.setProductName(item.getProductOrder().getName());
            orderDTO.setPrice(item.getPrice());
            orderDTO.setStatus(item.getStatus());
            orderDTO.setTotalPrice(item.getPrice() * item.getQuantity());
            orderDTO.setCreatedDate(item.getCreatedDate());
            orderDTO.setQuantity(item.getQuantity());
            results.add(orderDTO);
        }
        return results;
    }

    @Override
    public List<OrderDTO> findByUser(String userName) {
        List<OrderDTO> results = new ArrayList<>();
        List<OrderEntity> orderEntities = orderRepository.findByUserOrder_UserName(userName);
        for (OrderEntity item: orderEntities) {
            OrderDTO orderDTO = new OrderDTO();
            orderDTO.setId(item.getId());
            orderDTO.setFullName(item.getUserOrder().getFullName());
            orderDTO.setProductName(item.getProductOrder().getName());
            orderDTO.setPrice(item.getPrice());
            orderDTO.setStatus(item.getStatus());
            orderDTO.setCreatedDate(item.getCreatedDate());
            orderDTO.setTotalPrice(item.getPrice() * item.getQuantity());
            orderDTO.setQuantity(item.getQuantity());
            results.add(orderDTO);
        }
        return results;
    }

    @Override
    @Transactional
    public void updateStatus(OrderDTO orderDTO) {
        OrderEntity orderEntity = orderRepository.findOne(orderDTO.getId());
        orderEntity.setStatus(orderDTO.getStatus());
        if (orderDTO.getStatus().equals("customer_cancel")) {
            ProductEntity product = orderEntity.getProductOrder();
            product.setQuantity(product.getQuantity() + orderEntity.getQuantity());
            productRepository.save(product);
        }
        orderRepository.save(orderEntity);
    }

    @Override
    public List<OrderDTO> report(String fromDateStr, String toDateStr) {
        List<OrderDTO> results = new ArrayList<>();
        Date fromDate = StringUtils.isNotBlank(fromDateStr) ? DateUtils.convertStringToDate("dd-MM-yyyy", fromDateStr) : null;
        Date toDate = StringUtils.isNotBlank(toDateStr) ? DateUtils.convertStringToDate("dd-MM-yyyy", toDateStr) : null;
        List<OrderEntity> orderEntities = new ArrayList<>();
        if (fromDate != null && toDate == null) {
            orderEntities = orderRepository.findByCreatedDateAfter(fromDate);
        } else if (toDate != null && fromDate == null) {
            orderEntities = orderRepository.findByCreatedDateBefore(toDate);
        } else if (fromDate != null && toDate != null) {
            orderEntities = orderRepository.findByCreatedDateBetween(fromDate, toDate);
        }
        for (OrderEntity item: orderEntities) {
            OrderDTO orderDTO = new OrderDTO();
            orderDTO.setId(item.getId());
            orderDTO.setFullName(item.getUserOrder().getFullName());
            orderDTO.setProductName(item.getProductOrder().getName());
            orderDTO.setPrice(item.getPrice());
            orderDTO.setTotalPrice(item.getPrice() * item.getQuantity());
            orderDTO.setStatus(item.getStatus());
            orderDTO.setCreatedDate(item.getCreatedDate());
            orderDTO.setQuantity(item.getQuantity());
            results.add(orderDTO);
        }
        return results;
    }
}
