package com.curso.orderservice.dto;

public class InventoryResponseDto {

	private String skuCode;

	private Boolean isInStock;

	public String getSkuCode() {
		return skuCode;
	}

	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
	}

	public Boolean getIsInStock() {
		return isInStock;
	}

	public void setIsInStock(Boolean isInStock) {
		this.isInStock = isInStock;
	}

	public static InventoryResponseDto of(String skuCode, Boolean isInStock) {

		InventoryResponseDto dto = new InventoryResponseDto();
		dto.setSkuCode(skuCode);
		dto.setIsInStock(isInStock);

		return dto;
	}

}
