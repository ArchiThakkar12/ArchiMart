package com.archimart.repository;

import com.archimart.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "orderRepository")
public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findByCustomerEmailId(String customerEmailId);
}
