package com.curso.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.curso.orderservice.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

}
