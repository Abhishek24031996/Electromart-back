package com.abhi.electro.services.customer.cart;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;

import com.abhi.electro.dto.AddProductInDto;
import com.abhi.electro.dto.OrderDto;
import com.abhi.electro.dto.PlaceOrderDto;

public interface CartService {
	
	ResponseEntity<?> addProductToCart(AddProductInDto addProductInDto);
	
	 OrderDto getCartByUserId(Long userId);
	 
	 OrderDto applyCoupon(Long userId, String code);
	 
	 OrderDto increaseProductQuantity(AddProductInDto addProductInDto);
	 
	 OrderDto decreaseProductQuantity(AddProductInDto addProductInDto);
	 
	 OrderDto placeOrder(PlaceOrderDto placeOrderDto);
	 
	 List<OrderDto> getMyPlacedOrders(Long userId);
	 
	 OrderDto searchOrderByTrackingId(UUID trackingId);

}
