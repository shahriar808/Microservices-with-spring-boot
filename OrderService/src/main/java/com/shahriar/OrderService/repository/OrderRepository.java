package com.shahriar.OrderService.repository;

import com.shahriar.OrderService.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    // Additional query methods can be defined here if needed
}
