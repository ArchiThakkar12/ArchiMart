package com.archimart.repository;

import com.archimart.entity.CustomerCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository(value = "customerCartRepository")
public interface CustomerCartRepository extends JpaRepository<CustomerCart, Integer> {

    Optional<CustomerCart> findByCustomerEmailId(String customerEmailId);

}
