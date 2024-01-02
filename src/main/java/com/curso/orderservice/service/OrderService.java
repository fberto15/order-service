package com.curso.orderservice.service;

import com.curso.orderservice.dto.OrderRequestDto;

public interface OrderService {
	
	public void placeOrder(OrderRequestDto orderRequest);

}
