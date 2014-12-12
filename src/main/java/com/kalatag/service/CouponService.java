package com.kalatag.service;

import java.util.List;

import com.kalatag.domain.Coupon;

public interface CouponService extends CRUDService<Coupon>{
	
	Coupon verify(String code);
	Coupon redeem(Coupon coupon);
	List<Coupon> redeem(List<Coupon> coupons);

}
