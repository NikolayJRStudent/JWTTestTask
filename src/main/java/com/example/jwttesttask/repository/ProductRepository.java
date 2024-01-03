package com.example.jwttesttask.repository;

import com.example.jwttesttask.domain.Products;
import com.example.jwttesttask.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Products, Long> {
    List<Products> findByUser(Users user);
}
