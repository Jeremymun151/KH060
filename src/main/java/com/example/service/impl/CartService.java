package com.example.service.impl;

import com.example.constant.SystemConstant;
import com.example.dto.CartDTO;
import com.example.entity.OrderEntity;
import com.example.entity.ProductEntity;
import com.example.entity.UserEntity;
import com.example.repository.OrderRepository;
import com.example.repository.ProductRepository;
import com.example.repository.UserRepository;
import com.example.service.ICartService;
import com.example.service.IProductService;
import com.example.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CartService implements ICartService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public CartDTO findByProductId(long productId) {
        ProductEntity productEntity = productRepository.findOne(productId);
        CartDTO cartDTO = new CartDTO();
        cartDTO.setId(productEntity.getId());
        cartDTO.setName(productEntity.getName());
        cartDTO.setPrice(productEntity.getPrice());
        if (!productEntity.getEvent().getCode().equals("none")) {
            Integer basePrice = productEntity.getPrice();
            Integer percentDiscount = productEntity.getEvent().getPercent();
            Integer priceAfterDiscount = basePrice - ((basePrice * percentDiscount) / 100);
            cartDTO.setPriceDiscount(priceAfterDiscount);
        }
        return cartDTO;
    }

    @Override
    @Transactional
    public void saveCart(List<CartDTO> cartDTOS) {
        String userName = SecurityUtils.getPrincipal().getUsername();
        UserEntity user = userRepository.findOneByUserNameAndStatus(userName, 1);
        for (CartDTO item: cartDTOS) {
            OrderEntity order = new OrderEntity();
            if (item.getPriceDiscount() != null) {
                order.setPrice(item.getPriceDiscount());
            } else {
                order.setPrice(item.getPrice());
            }
            order.setStatus(SystemConstant.WAITING_CONFIRM);
            order.setQuantity(item.getQuantity());
            ProductEntity product = productRepository.findOne(item.getId());
            order.setUserOrder(user);
            order.setProductOrder(product);
            orderRepository.save(order);
            product.setQuantity(product.getQuantity() - item.getQuantity());
            productRepository.save(product);
        }
    }
}
