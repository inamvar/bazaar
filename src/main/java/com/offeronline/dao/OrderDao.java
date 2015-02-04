package com.offeronline.dao;

import java.util.Date;
import java.util.List;

import com.offeronline.domain.Deal;
import com.offeronline.domain.Order;
import com.offeronline.domain.OrderStatus;
import com.offeronline.domain.Person;

public interface OrderDao extends GenericDao<Order>{

	List<Order> findPendingOrders(Deal deal);
	List<Order> findOrdersByDeal(Deal deal);
	List<Order> findOrdersByCustomer(Person customer);
	List<Order> findOrders(Date startDate, Date endDate, String customerFisrtName,String customerLastName, String merchantName, String dealName,OrderStatus status , int id );
	int updateStatus(List<Order> orders, OrderStatus status);
}
