package com.example.jwttesttask.controller;


import com.example.jwttesttask.domain.Products;
import com.example.jwttesttask.dto.AddProductDto;
import com.example.jwttesttask.service.AuthService;
import com.example.jwttesttask.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MainController {

    private final ProductService productService;

    @PostMapping("/products/add")
    public ResponseEntity<?> Products(@RequestBody AddProductDto addProductDto, @RequestHeader("Authorization") String authorizationHeader){
       return productService.createNewProduct(addProductDto,authorizationHeader);
    }

    @GetMapping("/products/all")
    public ResponseEntity<?> getProducts(@RequestHeader("Authorization") String authorizationHeader){

        return productService.getAllProducts(authorizationHeader);
    }

}
