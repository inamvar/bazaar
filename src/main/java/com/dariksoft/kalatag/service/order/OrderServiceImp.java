package com.dariksoft.kalatag.service.order;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dariksoft.kalatag.dao.OrderDao;
import com.dariksoft.kalatag.domain.Coupon;
import com.dariksoft.kalatag.domain.Deal;
import com.dariksoft.kalatag.domain.ItemStatus;
import com.dariksoft.kalatag.domain.Order;
import com.dariksoft.kalatag.domain.OrderStatus;
import com.dariksoft.kalatag.exception.DealExpiredException;
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

		Deal deal = order.getDeal();
		System.out.println("deal.id = " + deal.getId());
		int minimum = deal.getMinCoupon();
		List<Order> orders = new ArrayList<Order>();
		if (dealService.getSold(deal) >= minimum) {

			List<Order> pendings = this.findPendingOrders(deal);
			boolean exist = false;
			for(Order pending : pendings){
				if(pending.getId() == order.getId()){
					exist = true;
				}
				orders.add(pending);
				
			}
			
			if(!exist)
				orders.add(order);
			
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

	@Override
	@Transactional
	public List<Order> findOrdersByDeal(Deal deal) {
		return orderDao.findOrdersByDeal(deal);
	}

}
