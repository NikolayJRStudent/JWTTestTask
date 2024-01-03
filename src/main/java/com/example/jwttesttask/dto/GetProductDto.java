package com.example.jwttesttask.dto;

import com.example.jwttesttask.domain.Products;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
public class GetProductDto {
    List<Products> products;
}
