package com.abhi.electro.controller.customer;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abhi.electro.dto.AddProductInDto;
import com.abhi.electro.dto.OrderDto;
import com.abhi.electro.dto.PlaceOrderDto;
import com.abhi.electro.exceptions.ValidationException;
import com.abhi.electro.services.customer.cart.CartService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/customer")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class CartController {
	
	private final CartService cartService;

	public CartController(CartService cartService) {
		super();
		this.cartService = cartService;
	}
	
	@PostMapping("/cart")
	public ResponseEntity<?> addProductToCart(@RequestBody AddProductInDto addProductInDto){
		
		return cartService.addProductToCart(addProductInDto);	
	}
	
	@GetMapping("/cart/{userId}")
	public ResponseEntity<?> addProductToCart(@PathVariable Long userId  ){
		OrderDto orderDto = cartService.getCartByUserId(userId);
		
		return ResponseEntity.status(HttpStatus.OK).body(orderDto);	
	}
	
	@GetMapping("/coupon/{userId}/{code}")
	public ResponseEntity<?> applyCoupon(@PathVariable Long userId, @PathVariable String code){
		try {
			OrderDto orderDto = cartService.applyCoupon(userId, code);
			return ResponseEntity.ok(orderDto);
		} catch (ValidationException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		}
	}
	
	@PostMapping("/addition")
	public ResponseEntity<OrderDto> increaseProductQuantity(@RequestBody AddProductInDto addProductInDto){
		
		return ResponseEntity.status(HttpStatus.CREATED).body(cartService.increaseProductQuantity(addProductInDto))	;
	}
	
	@PostMapping("/deduction")
	public ResponseEntity<OrderDto> decreaseProductQuantity(@RequestBody AddProductInDto addProductInDto){
		
		return ResponseEntity.status(HttpStatus.CREATED).body(cartService.decreaseProductQuantity(addProductInDto))	;
	}
	
	@PostMapping("/placeOrder")
	public ResponseEntity<OrderDto> placeOrder(@RequestBody PlaceOrderDto placeOrderDto){
		
		return ResponseEntity.status(HttpStatus.CREATED).body(cartService.placeOrder(placeOrderDto));
	}
	
	@GetMapping("/myOrders/{userId}")
	public ResponseEntity<List<OrderDto>> getMyPlacedOrders(@PathVariable Long userId){
		return ResponseEntity.ok(cartService.getMyPlacedOrders(userId));
	}

}
