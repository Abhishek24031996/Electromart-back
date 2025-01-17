package com.abhi.electro.dto;

import java.util.List;

import lombok.Data;

@Data
public class ProductDetailDto {

	private ProductDto productDto;
	
	private List<ReviewDto> reviewDtoList;
	
	private List<FAQDto> faqDtoList;

	public ProductDto getProductDto() {
		return productDto;
	}

	public void setProductDto(ProductDto productDto) {
		this.productDto = productDto;
	}

	

	public List<ReviewDto> getReviewDtoList() {
		return reviewDtoList;
	}

	public void setReviewDtoList(List<ReviewDto> reviewDtoList) {
		this.reviewDtoList = reviewDtoList;
	}

	public List<FAQDto> getFaqDtoList() {
		return faqDtoList;
	}

	public void setFaqDtoList(List<FAQDto> faqDtoList) {
		this.faqDtoList = faqDtoList;
	}
	
	
}
