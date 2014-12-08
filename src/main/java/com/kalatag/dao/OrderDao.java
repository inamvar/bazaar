package com.kalatag.dao;

import java.util.List;

import com.kalatag.domain.Deal;
import com.kalatag.domain.Order;

public interface OrderDao extends GenericDao<Order>{

	List<Order> findPendingOrders(Deal deal);
	List<Order> findOrdersByDeal(Deal deal);
}
