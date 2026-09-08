package com.example.SecurityPractice.repository;

import com.example.SecurityPractice.model.Product;
import com.example.SecurityPractice.projection.ProductProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<Product,Integer> {

    @Query(value = """
            select pro.name,
            pro.qty,
            pro.price
            from Product pro where pro.id=:id
            """)
    ProductProjection findProductById(@Param("id") Integer id);
}
