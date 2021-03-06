package com.offeronline.dao;

import java.util.List;

import com.offeronline.domain.Coupon;

public interface CouponDao extends GenericDao<Coupon> {
	
	Coupon verify(String code);
	Coupon redeem(Coupon coupon);
	List<Coupon> redeem(List<Coupon> coupons);
	Coupon findByCode(String code);
}
