package com.curso.orderservice.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import com.curso.orderservice.dto.InventoryResponseDto;
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

	@Autowired
	private WebClient webClient;

	@Override
	public void placeOrder(OrderRequestDto orderRequest) {

		Order order = new Order();
		order.setOrderNumber(UUID.randomUUID().toString());

		List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDtos().stream()
				.map(orderLineItemsDto -> mapToDto(orderLineItemsDto)).toList();

		order.setOrderLineItemsList(orderLineItems);

		List<String> skuCodeList = order.getOrderLineItemsList().stream()
				.map(orderLineItem -> orderLineItem.getSkuCode()).toList();

		// llamar a inventory-service y colocar orden si product tiene stock
		InventoryResponseDto[] inventoryResponse = webClient.get()
				.uri("http://localhost:8082/api/inventory", uriBuilder -> uriBuilder.queryParam("skuCodeList", skuCodeList).build())
				.retrieve()
				.bodyToMono(InventoryResponseDto[].class)
				.block();
		
		boolean allProductsInStock = Arrays.stream(inventoryResponse).allMatch(inventory -> inventory.getIsInStock());

		if (allProductsInStock) {
			orderRepository.save(order);
		} else {
			throw new IllegalArgumentException("products sin stock");
		}

	}

	private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
		OrderLineItems orderLineItems = new OrderLineItems();
		orderLineItems.setPrice(orderLineItemsDto.getPrice());
		orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
		orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
		return orderLineItems;
	}

}
