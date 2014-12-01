package com.dariksoft.kalatag.service.order;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dariksoft.kalatag.dao.OrderDao;
import com.dariksoft.kalatag.domain.Coupon;
import com.dariksoft.kalatag.domain.Deal;
import com.dariksoft.kalatag.domain.Order;
import com.dariksoft.kalatag.domain.OrderStatus;
import com.dariksoft.kalatag.service.CRUDServiceImp;
import com.dariksoft.kalatag.service.CouponService;
import com.dariksoft.kalatag.service.DealService;

@Service
public class OrderServiceImp extends CRUDServiceImp<Order> implements
		OrderService {

	@Autowired
	DealService dealService;

	@Autowired
	OrderDao orderDao;

	@Autowired
	CouponService couponService;


	@Override
	@Transactional
	public List<Order> confirmOrder(Order order) {
		
		System.out.println( "Confirming  order number "+order.getId());
		Deal deal = order.getDeal();
       // Hibernate.initialize(deal.getImages());
       // Hibernate.initialize(deal.getThumbnail());
		System.out.println("deal.id = " + deal.getId());
		int minimum = deal.getMinCoupon();
		List<Order> orders = new ArrayList<Order>();
		if (minimum == 0
				|| dealService.getSold(deal) + order.getQuantity() >= minimum) {

			orders.add(order);
			if (minimum > 0) {
				orders.addAll(this.findPendingOrders(deal));
			}
			if (orders.size() > 0) {
				for (Order ord : orders) {
					List<Coupon> coupons = new ArrayList<Coupon>();
					for (int i = 0; i < ord.getQuantity(); i++) {
						
						Coupon coupon = new Coupon();
						coupon.setDeal(deal);
						coupon.setPrice(ord.getOption().getPrice());
						coupon.setOrder(ord);
						coupons.add(coupon);
						
					}
					ord.setCoupons(coupons);
					ord.setStatus(OrderStatus.DONE);
					ord.setFinilizeDate(new Date());
					ord = update(ord);
				}
			}
		}

		return orders;
	}

	@Override
	@Transactional
	public List<Order> findPendingOrders(Deal deal) {
		return orderDao.findPendingOrders(deal);
	}

}
