package com.example.api.web;

import com.example.dto.CommentDTO;
import com.example.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController(value = "commentApiOfWeb")
@RequestMapping(value = "/api/comment")
public class CommentAPI {

    @Autowired
    private IProductService productService;

    @PostMapping
    public ResponseEntity<Void> addComment(@RequestBody CommentDTO comment) {
        productService.saveComment(comment);
        return ResponseEntity.noContent().build();
    }
}
