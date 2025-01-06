package com.archimart.repository;

import com.archimart.entity.CustomerCart;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CustomerCartRepository extends JpaRepository<CustomerCart, Integer> {

    Optional<CustomerCart> findByCustomerEmailId(String customerEmailId);

}
