package com.offeronline.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.offeronline.domain.Coupon;
import com.offeronline.domain.CouponStatus;

@Repository
public class CouponDaoImp extends GenericDaoImp<Coupon> implements CouponDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Coupon redeem(Coupon coupon) {

		String hql = "UPDATE Coupon set status = :status , redeemDate = :date "
				+ "WHERE id = :couponId";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("status", CouponStatus.REDEMED);
		query.setParameter("couponId", coupon.getId());
		Date now = new Date();
		query.setParameter("date",now);
		int result = query.executeUpdate();
		if (result > 0){
			coupon.setStatus(CouponStatus.REDEMED);
			coupon.setRedeemDate(now);
		}
		return coupon;
	}

	@Override
	public List<Coupon> redeem(List<Coupon> coupons) {

		List<Integer> ids = new ArrayList<Integer>();

		for (Coupon coupon : coupons) {
			ids.add(coupon.getId());
		}

		String hql = "UPDATE Coupon set status = :status , redeemDate = :date "
				+ "WHERE id in (:idList)";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("status", CouponStatus.REDEMED);
		query.setParameter("idList", ids);
		Date now = new Date();
		query.setParameter("date", now);
		int result = query.executeUpdate();
		if (result == ids.size())
			for (Coupon coupon : coupons) {
				coupon.setStatus(CouponStatus.REDEMED);
				coupon.setRedeemDate(now);
			}
		return coupons;
	}

	@Override
	public Coupon verify(String code) {
		Coupon coupon = null;
		String hql = "FROM  Coupon C WHERE C.code = :code";
		Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("code", code);
		query.setMaxResults(1);
		coupon = (Coupon) query.uniqueResult();
		return coupon;

	}

	@Override
	public Coupon findByCode(String code) {
		Coupon coupon = null;
		String hql = "FROM  Coupon C WHERE C.code = :code";
		Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("code", code);
		query.setMaxResults(1);
		coupon = (Coupon) query.uniqueResult();
		return coupon;
	}

}
