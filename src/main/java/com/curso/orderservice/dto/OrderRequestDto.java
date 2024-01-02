package com.curso.orderservice.dto;

import java.util.List;

public class OrderRequestDto {

	private List<OrderLineItemsDto> orderLineItemsDtos;

	public List<OrderLineItemsDto> getOrderLineItemsDtos() {
		return orderLineItemsDtos;
	}

	public void setOrderLineItemsDtos(List<OrderLineItemsDto> orderLineItemsDtos) {
		this.orderLineItemsDtos = orderLineItemsDtos;
	}

}
