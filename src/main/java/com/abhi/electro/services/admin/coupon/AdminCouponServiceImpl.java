package com.abhi.electro.services.admin.coupon;

import java.util.List;

import org.springframework.stereotype.Service;

import com.abhi.electro.entity.Coupon;
import com.abhi.electro.exceptions.ValidationException;
import com.abhi.electro.repository.CouponRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminCouponServiceImpl implements AdminCouponService {
	
	private final CouponRepository couponRepository;

	public AdminCouponServiceImpl(CouponRepository couponRepository) {
		super();
		this.couponRepository = couponRepository;
	}

	public Coupon createCoupon(Coupon coupon) {
		if(couponRepository.existsByCode(coupon.getCode())) {
			throw new ValidationException("Coupon code already exists.");
		}
		return couponRepository.save(coupon);
	}
	
	public List<Coupon> getAllCoupons(){
		return couponRepository.findAll();
	}
}
