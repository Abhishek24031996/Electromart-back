package com.abhi.electro.services.admin.coupon;

import java.util.List;

import com.abhi.electro.entity.Coupon;

public interface AdminCouponService {
	
	Coupon createCoupon(Coupon coupon);
	
	List<Coupon> getAllCoupons();

}
