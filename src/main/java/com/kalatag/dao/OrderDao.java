package com.kalatag.dao;

import java.util.List;

import com.kalatag.domain.Deal;
import com.kalatag.domain.Order;
import com.kalatag.domain.Person;

public interface OrderDao extends GenericDao<Order>{

	List<Order> findPendingOrders(Deal deal);
	List<Order> findOrdersByDeal(Deal deal);
	List<Order> findOrdersByCustomer(Person customer);
}
