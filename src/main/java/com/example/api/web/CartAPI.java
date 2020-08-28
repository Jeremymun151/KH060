package com.example.api.web;

import com.example.dto.CartDTO;
import com.example.service.ICartService;
import com.example.util.SecurityUtils;
import com.example.util.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController(value = "cartApiOfWeb")
@RequestMapping(value = "/api/cart")
public class CartAPI {

    @Autowired
    private ICartService cartService;

    @PostMapping("/{productId}")
    public ResponseEntity<Void> addToCart(@PathVariable("productId") long productId, HttpServletRequest request) {
        String userName = SecurityUtils.getPrincipal().getUsername();
        List<CartDTO> cartDTOS = (List<CartDTO>) SessionUtils.getInstance().getValue(request, userName);
        CartDTO cartDTO = cartService.findByProductId(productId);
        if (cartDTOS == null) {
            cartDTOS = new ArrayList<>();
        } else {
            SessionUtils.getInstance().remove(request, userName);
        }
        cartDTOS.add(cartDTO);
        SessionUtils.getInstance().putValue(request, userName, cartDTOS);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<Void> updatePrice(@RequestBody CartDTO cartDTO, HttpServletRequest request) {
        String userName = SecurityUtils.getPrincipal().getUsername();
        List<CartDTO> cartDTOS = (List<CartDTO>) SessionUtils.getInstance().getValue(request, userName);
        SessionUtils.getInstance().remove(request, userName);
        for (CartDTO item: cartDTOS) {
            if (item.getId() == cartDTO.getId()) {
                item.setQuantity(cartDTO.getQuantity());
                if (item.getPriceDiscount() != null) {
                    item.setTotalPrice(cartDTO.getQuantity() * item.getPriceDiscount());
                } else {
                    item.setTotalPrice(cartDTO.getQuantity() * item.getPrice());
                }
            }
        }
        SessionUtils.getInstance().putValue(request, userName, cartDTOS);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteCart(@RequestBody Long[] ids, HttpServletRequest request) {
        String userName = SecurityUtils.getPrincipal().getUsername();
        List<CartDTO> cartDTOS = (List<CartDTO>) SessionUtils.getInstance().getValue(request, userName);
        List<CartDTO> cartDtoNews = new ArrayList<>();
        SessionUtils.getInstance().remove(request, userName);
        List<Long> idLongs = Arrays.asList(ids);
        for (CartDTO item: cartDTOS) {
            if (!idLongs.contains(item.getId())) {
                cartDtoNews.add(item);
            }
        }
        SessionUtils.getInstance().putValue(request, userName, cartDtoNews);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/checkout")
    public ResponseEntity<Void> checkout(HttpServletRequest request) {
        String userName = SecurityUtils.getPrincipal().getUsername();
        List<CartDTO> cartDTOS = (List<CartDTO>) SessionUtils.getInstance().getValue(request, userName);
        cartService.saveCart(cartDTOS);
        SessionUtils.getInstance().remove(request, userName);
        return ResponseEntity.noContent().build();
    }
}
