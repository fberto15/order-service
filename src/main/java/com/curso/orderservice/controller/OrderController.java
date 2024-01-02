package com.curso.orderservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.curso.orderservice.dto.OrderRequestDto;
import com.curso.orderservice.service.OrderService;

@RestController
@RequestMapping("/api/order")
public class OrderController {
	
	@Autowired
	private OrderService orderService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public String placeOrder(@RequestBody OrderRequestDto orderRequest) {
		
		orderService.placeOrder(orderRequest);
		
		return "Order placed succesufully";
	}
}
