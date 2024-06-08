package com.abhi.electro.services.customer;

import java.util.List;

import com.abhi.electro.dto.ProductDetailDto;
import com.abhi.electro.dto.ProductDto;

public interface CustomerProductService {
	
	List<ProductDto> searchProductByTitle(String title);
	
	List<ProductDto> getAllProducts();
	
	ProductDetailDto getProductDetailById(Long productId);

}
