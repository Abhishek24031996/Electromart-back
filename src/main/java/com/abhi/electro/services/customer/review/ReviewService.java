package com.abhi.electro.services.customer.review;

import java.io.IOException;

import com.abhi.electro.dto.OrderedProductResponseDto;
import com.abhi.electro.dto.ReviewDto;

public interface ReviewService {
	
	OrderedProductResponseDto getOrderedProductDetailsByOrderId(Long orderId);
	
	ReviewDto giveReview(ReviewDto reviewDto) throws IOException;

}
