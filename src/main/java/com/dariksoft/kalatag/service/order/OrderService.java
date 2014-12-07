package com.dariksoft.kalatag.service.order;

import java.util.List;

import com.dariksoft.kalatag.domain.Deal;
import com.dariksoft.kalatag.domain.Order;
import com.dariksoft.kalatag.service.CRUDService;

public interface OrderService extends CRUDService<Order>{

	List<Order> confirmOrder(Order order);
	List<Order> findPendingOrders(Deal deal);
	List<Order> findOrdersByDeal(Deal deal);
}
