package com.kalatag.service.order;

import java.util.List;

import com.kalatag.domain.Deal;
import com.kalatag.domain.Order;
import com.kalatag.service.CRUDService;

public interface OrderService extends CRUDService<Order>{

	List<Order> confirmOrder(Order order);
	List<Order> findPendingOrders(Deal deal);
	List<Order> findOrdersByDeal(Deal deal);
}
