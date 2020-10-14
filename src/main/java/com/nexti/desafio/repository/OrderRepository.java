package com.nexti.desafio.repository;

import com.nexti.desafio.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
