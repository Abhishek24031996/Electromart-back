package com.abhi.electro.services.customer.wishlist;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.abhi.electro.dto.WishlistDto;
import com.abhi.electro.entity.Product;
import com.abhi.electro.entity.User;
import com.abhi.electro.entity.Wishlist;
import com.abhi.electro.repository.ProductRepository;
import com.abhi.electro.repository.UserRepository;
import com.abhi.electro.repository.WishlistRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WishlistServiceImpl implements WishlistService {

	private final UserRepository userRepository;
	
	private final ProductRepository productRepository;
	
	private final WishlistRepository wishlistRepository;

	public WishlistServiceImpl(UserRepository userRepository, ProductRepository productRepository,
			WishlistRepository wishlistRepository) {
		super();
		this.userRepository = userRepository;
		this.productRepository = productRepository;
		this.wishlistRepository = wishlistRepository;
	}
	
	public WishlistDto addProductToWishlist(WishlistDto wishlistDto) {
		Optional<Product> optionalProduct = productRepository.findById(wishlistDto.getProductId());
		Optional<User> optionalUser = userRepository.findById(wishlistDto.getUserId());
		
		if(optionalProduct.isPresent() && optionalUser.isPresent()) {
			Wishlist wishlist = new Wishlist();
			wishlist.setProduct(optionalProduct.get());
			wishlist.setUser(optionalUser.get());
			
			return wishlistRepository.save(wishlist).getWishlistDto();
		}
		return null;
	}
	
	public List<WishlistDto> getWishlistByUserId(Long userId){
		return wishlistRepository.findAllByUserId(userId).stream().map(Wishlist::getWishlistDto).collect(Collectors.toList());
	}
}
