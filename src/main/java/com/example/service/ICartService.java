package com.example.service;

import com.example.dto.CartDTO;

import java.util.List;

public interface ICartService {
    CartDTO findByProductId(long productId);
    void saveCart(List<CartDTO> cartDTOS);
}
