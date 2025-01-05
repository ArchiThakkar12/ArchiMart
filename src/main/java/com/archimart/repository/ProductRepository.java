package com.archimart.repository;

import com.archimart.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository(value ="productRepository")
public interface ProductRepository extends JpaRepository<Product, Integer> {
}
