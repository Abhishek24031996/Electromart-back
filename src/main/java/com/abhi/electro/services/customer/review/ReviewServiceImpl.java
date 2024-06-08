package com.abhi.electro.services.customer.review;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.abhi.electro.dto.OrderedProductResponseDto;
import com.abhi.electro.dto.ProductDto;
import com.abhi.electro.dto.ReviewDto;
import com.abhi.electro.entity.CartItems;
import com.abhi.electro.entity.Order;
import com.abhi.electro.entity.Product;
import com.abhi.electro.entity.Review;
import com.abhi.electro.entity.User;
import com.abhi.electro.repository.OrderRepository;
import com.abhi.electro.repository.ProductRepository;
import com.abhi.electro.repository.ReviewRepository;
import com.abhi.electro.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
	
	private final OrderRepository orderRepository;

	private final ProductRepository productRepository;
	
	private final UserRepository userRepository;
	
	private final ReviewRepository reviewRepository;

	public ReviewServiceImpl(OrderRepository orderRepository, ProductRepository productRepository,
			UserRepository userRepository, ReviewRepository reviewRepository) {
		super();
		this.orderRepository = orderRepository;
		this.productRepository = productRepository;
		this.userRepository = userRepository;
		this.reviewRepository = reviewRepository;
	}

	public OrderedProductResponseDto getOrderedProductDetailsByOrderId(Long orderId) {
	    System.out.println("Received orderId: " + orderId); // Log the received orderId
		Optional<Order> optionalOrder = orderRepository.findById(orderId);
		OrderedProductResponseDto orderedProductResponseDto = new OrderedProductResponseDto();
		if(optionalOrder.isPresent()) {
			orderedProductResponseDto.setOrderAmount(optionalOrder.get().getAmount());
			
			List<ProductDto> productDtoList = new ArrayList<>();
			for (CartItems cartItems: optionalOrder.get().getCartItems()) {
				ProductDto productDto = new ProductDto();
				
				productDto.setId(cartItems.getProduct().getId());
				productDto.setName(cartItems.getProduct().getName());
				productDto.setPrice(cartItems.getPrice());
				productDto.setQuantity(cartItems.getQuantity());
				
				productDto.setByteimg(cartItems.getProduct().getImg());
				
				productDtoList.add(productDto);
			}
			orderedProductResponseDto.setProductDtoList(productDtoList);
		} else {
	        System.out.println("Order not found for orderId: " + orderId); // Log if order is not found
		}
		return orderedProductResponseDto;
	}
	
	public ReviewDto giveReview(ReviewDto reviewDto) throws IOException {
		Optional<Product> optionalProduct = productRepository.findById(reviewDto.getProductId());
		Optional<User> optionalUser = userRepository.findById(reviewDto.getUserId());
		
		if(optionalProduct.isPresent() && optionalUser.isPresent()) {
			Review review = new Review();
			
			review.setRating(reviewDto.getRating());
			review.setDescription(reviewDto.getDescription());
			review.setUser(optionalUser.get());
			review.setProduct(optionalProduct.get());
			review.setImg(reviewDto.getImg().getBytes());
			
			return reviewRepository.save(review).getDto();
		}
		return null;
	}

}
