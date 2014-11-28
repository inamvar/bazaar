package com.dariksoft.kalatag.service.order;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dariksoft.kalatag.domain.Coupon;
import com.dariksoft.kalatag.domain.Customer;
import com.dariksoft.kalatag.domain.Deal;
import com.dariksoft.kalatag.domain.DealOption;
import com.dariksoft.kalatag.domain.Order;
import com.dariksoft.kalatag.domain.OrderStatus;
import com.dariksoft.kalatag.service.CRUDServiceImp;
import com.dariksoft.kalatag.service.DealService;

@Service
public class OrderServiceImp extends CRUDServiceImp<Order> implements OrderService{

	
	@Autowired
	DealService dealService;
	

	@Override
	public Order newOrder(Deal deal,DealOption option, Customer customer, int qty) {
		
		if(deal !=null && customer !=null){
				Order order = new Order();
				order.setOrderDate(new Date());
				order.setCustomer(customer);
				order.setDeal(deal);
				order.setCoupons(new ArrayList<Coupon>());
				order.setQuantity(qty);
				order.setTotalPrice(deal.getPrice() * qty);
				order.setStatus(OrderStatus.PENDING);
				if(option != null){
					order.setOption(option);
					order.setTotalPrice((deal.getPrice() - ( (option.getDiscount()/100) * deal.getPrice() )) * qty);
				}
				return create(order);
		}else{
			return null;
		}
	
	}
}
