package com.abhi.electro.controller;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.abhi.electro.dto.OrderDto;
import com.abhi.electro.services.customer.cart.CartService;

import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class TrackingController {
	
	private final CartService cartService;

	public TrackingController(CartService cartService) {
		super();
		this.cartService = cartService;
	}
	
	@GetMapping("/order/{trackingId}")
	public ResponseEntity<OrderDto> searchOrderByTrackingId(@PathVariable UUID trackingId){
		OrderDto orderDto = cartService.searchOrderByTrackingId(trackingId);
		if(orderDto == null) return ResponseEntity.notFound().build();
		return ResponseEntity.ok(orderDto);
	}

}
