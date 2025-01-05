package com.archimart.repository;

import com.archimart.entity.CartProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository(value = "cartProductRepository")
public interface CartProductRepository extends JpaRepository<CartProduct, Integer> {

}
