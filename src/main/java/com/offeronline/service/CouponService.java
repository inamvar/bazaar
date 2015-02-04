package com.offeronline.service;

import java.util.List;

import com.offeronline.domain.Coupon;

public interface CouponService extends CRUDService<Coupon>{
	
	Coupon verify(String code);
	Coupon redeem(Coupon coupon);
	Coupon findByCode(String code);
	List<Coupon> redeem(List<Coupon> coupons);
	

}
