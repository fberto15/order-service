package com.curso.orderservice.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.curso.orderservice.dto.OrderLineItemsDto;
import com.curso.orderservice.dto.OrderRequestDto;
import com.curso.orderservice.model.Order;
import com.curso.orderservice.model.OrderLineItems;
import com.curso.orderservice.repository.OrderRepository;
import com.curso.orderservice.service.OrderService;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderRepository orderRepository;

	@Override
	public void placeOrder(OrderRequestDto orderRequest) {

		Order order = new Order();
		order.setOrderNumber(UUID.randomUUID().toString());

		List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDtos().stream()
				.map(orderLineItemsDto -> mapToDto(orderLineItemsDto)).toList();
		
		order.setOrderLineItemsList(orderLineItems);
		
		orderRepository.save(order);

	}

	private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
		OrderLineItems orderLineItems = new OrderLineItems();
		orderLineItems.setPrice(orderLineItemsDto.getPrice());
		orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
		orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
		return orderLineItems;
	}

}
