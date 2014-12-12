package com.kalatag.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kalatag.dao.CouponDao;
import com.kalatag.domain.Coupon;

@Service
public class CouponServiceImp extends CRUDServiceImp<Coupon> implements
		CouponService {

	@Autowired
	CouponDao couponDao;

	@Override
	@Transactional
	public Coupon verify(String code) {
		return couponDao.verify(code);
	}

	@Override
	@Transactional
	public Coupon redeem(Coupon coupon) {
		return couponDao.redeem(coupon);
	}

	@Override
	@Transactional
	public List<Coupon> redeem(List<Coupon> coupons) {
		return couponDao.redeem(coupons);
	}

}
