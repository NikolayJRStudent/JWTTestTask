package com.example.jwttesttask.service;

import com.example.jwttesttask.domain.Products;
import com.example.jwttesttask.domain.Users;
import com.example.jwttesttask.dto.AddProductDto;
import com.example.jwttesttask.dto.GetProductDto;
import com.example.jwttesttask.exception.AppError;
import com.example.jwttesttask.repository.ProductRepository;
import com.example.jwttesttask.utils.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final JwtTokenUtils jwtTokenUtils;
    private final UserService userService;
    private final ProductRepository productRepository;
    public ResponseEntity<?> createNewProduct(@RequestBody AddProductDto addProductDto,@RequestHeader("Authorization") String authorizationHeader){


        Products product = new Products();
        product.setUser(findUserFromToken(authorizationHeader).get());
        product.setEntryDate(addProductDto.getEntryDate());
        product.setItemCode(addProductDto.getItemCode());
        product.setItemName(addProductDto.getItemName());
        product.setItemQuantity(addProductDto.getItemQuantity());
        product.setStatus(addProductDto.getStatus());
        productRepository.save(product);
        return ResponseEntity.ok("Product add");
    }

    public ResponseEntity<?> getAllProducts(@RequestHeader("Authorization") String authorizationHeader){

        List<Products> products = productRepository.findByUser(findUserFromToken(authorizationHeader).get());
        if (products.isEmpty()){
            return ResponseEntity.ok("You have`t product");
        }

        return  new ResponseEntity<>(products, HttpStatus.OK);
    }

    private String extractTokenFromAuthorizationHeader(String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7);
        }

        return null;
    }

    public Optional<Users> findUserFromToken(String authorizationHeader){

        String token = extractTokenFromAuthorizationHeader(authorizationHeader);
        if (token != null){
            String username = jwtTokenUtils.getUsername(token);
            Optional<Users> user = userService.findByUsername(username);
            return user;
        }
        return null;
    }
}
