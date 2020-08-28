package com.example.service;

import com.example.dto.CommentDTO;
import com.example.dto.HomeSearchDTO;
import com.example.dto.ProductDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface IProductService {
    List<ProductDTO> findAll(String name, Pageable pageable);
    List<ProductDTO> findAll(HomeSearchDTO homeSearchDTO);
    int getTotalItems(String name);
    ProductDTO insert(ProductDTO productDTO);
    ProductDTO findById(long id);
    ProductDTO update(ProductDTO productDTO);
    void deleteProduct(long[] ids);
    void saveComment(CommentDTO comment);
    List<CommentDTO> findComment(long productId);
    Map<String, String> getEvents();
}
