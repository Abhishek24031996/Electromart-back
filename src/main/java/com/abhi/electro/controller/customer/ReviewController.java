package com.abhi.electro.controller.customer;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abhi.electro.dto.OrderedProductResponseDto;
import com.abhi.electro.dto.ReviewDto;
import com.abhi.electro.services.customer.review.ReviewService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/customer")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class ReviewController {
	
	private final ReviewService reviewService;

	public ReviewController(ReviewService reviewService) {
		super();
		this.reviewService = reviewService;
	}
	
	@GetMapping("/ordered-products/{orderId}")
	public ResponseEntity<OrderedProductResponseDto> getOrderedProductDetailsByOrderId(@PathVariable Long orderId){
		return ResponseEntity.ok(reviewService.getOrderedProductDetailsByOrderId(orderId));
	}
	
	@PostMapping("/review")
	public ResponseEntity<?> giveReview(@ModelAttribute ReviewDto reviewDto) throws IOException{
		ReviewDto reviewDto1 = reviewService.giveReview(reviewDto);
		if(reviewDto1 == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went wrong!");
		return ResponseEntity.status(HttpStatus.CREATED).body(reviewDto1);
	}

}
