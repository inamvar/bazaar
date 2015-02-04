package com.offeronline.service.listener;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.offeronline.domain.Order;
import com.offeronline.domain.OrderStatus;
import com.offeronline.service.order.OrderService;

@Component("canceledOrdersListener")	
public class CanceledOrdersListener {
	
	
	private static final Logger logger = LoggerFactory
			.getLogger(CanceledOrdersListener.class);
	
	@Autowired
	OrderService orderService;
	
	
	public void onMessage(Order order){
		
		
		//TODO: reverse money will implemented here for canceled order;
		order.setStatus(OrderStatus.REVERSED);
		List<Order> canceledOrders = new ArrayList<Order>();
		canceledOrders.add(order);
		orderService.updateStatus(canceledOrders,
				OrderStatus.REVERSED);
		logger.info(String.format("Order number {0} reversed successfully", order.getId()));
	} 
}
