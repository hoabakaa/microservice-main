package com.caonhatlong.orderservice.repository;

import com.caonhatlong.orderservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
