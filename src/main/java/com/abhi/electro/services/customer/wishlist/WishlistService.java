package com.abhi.electro.services.customer.wishlist;

import java.util.List;

import com.abhi.electro.dto.WishlistDto;

public interface WishlistService {

	WishlistDto addProductToWishlist(WishlistDto wishlistDto);
	
	List<WishlistDto> getWishlistByUserId(Long userId);
}
