package com.curso.orderservice.dto;

import java.math.BigDecimal;

public class OrderLineItemsDto {

	private Long id;

	private String skuCode;

	private BigDecimal price;

	private Integer quantity;

	public static OrderLineItemsDto of(Long id, String skuCode, BigDecimal price, Integer quantity) {

		OrderLineItemsDto dto = new OrderLineItemsDto();
		dto.setId(id);
		dto.setSkuCode(skuCode);
		dto.setPrice(price);
		dto.setQuantity(quantity);

		return dto;

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSkuCode() {
		return skuCode;
	}

	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
}
