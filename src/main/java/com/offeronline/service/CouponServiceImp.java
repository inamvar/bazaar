package com.offeronline.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.offeronline.dao.CouponDao;
import com.offeronline.domain.Coupon;

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

		if (coupon != null) {
			//if (coupon.getExpireDate().compareTo(new Date()) < 0)
				return couponDao.redeem(coupon);
/*			else
				return null;*/
		}
		return null;
	}

	@Override
	@Transactional
	public List<Coupon> redeem(List<Coupon> coupons) {
		return couponDao.redeem(coupons);
	}

	@Override
	@Transactional
	public Coupon findByCode(String code) {
		return couponDao.findByCode(code);
	}

}
