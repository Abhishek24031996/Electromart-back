package com.abhi.electro.dto;

import java.util.List;

import lombok.Data;

@Data
public class OrderedProductResponseDto {
	
	private List<ProductDto> productDtoList;
	
	private Long orderAmount;

	public List<ProductDto> getProductDtoList() {
		return productDtoList;
	}

	public void setProductDtoList(List<ProductDto> productDtoList) {
		this.productDtoList = productDtoList;
	}

	public Long getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(Long orderAmount) {
		this.orderAmount = orderAmount;
	}
	
	

}
