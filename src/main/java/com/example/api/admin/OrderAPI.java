package com.example.api.admin;

import com.example.dto.CartDTO;
import com.example.dto.OrderDTO;
import com.example.service.IOrderService;
import com.example.util.SecurityUtils;
import com.example.util.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController(value = "orderApiOfAdmin")
@RequestMapping(value = "/api/order")
public class OrderAPI {

    @Autowired
    private IOrderService orderService;

    @PutMapping
    public ResponseEntity<Void> updateStatus(@RequestBody OrderDTO orderDTO) {
        orderService.updateStatus(orderDTO);
        return ResponseEntity.noContent().build();
    }
}
