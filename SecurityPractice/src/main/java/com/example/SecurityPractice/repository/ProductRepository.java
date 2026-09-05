package com.example.SecurityPractice.repository;

import com.example.SecurityPractice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Integer> {
}
