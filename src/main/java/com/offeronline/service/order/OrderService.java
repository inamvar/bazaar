package com.offeronline.service.order;

import java.util.Date;
import java.util.List;

import com.offeronline.domain.Deal;
import com.offeronline.domain.Order;
import com.offeronline.domain.OrderStatus;
import com.offeronline.domain.Person;
import com.offeronline.domain.Transaction;
import com.offeronline.exception.DealExpiredException;
import com.offeronline.service.CRUDService;

public interface OrderService extends CRUDService<Order>{

	List<Order> confirmOrder(Order order);
	List<Order> findPendingOrders(Deal deal);
	List<Order> findOrdersByDeal(Deal deal);
	List<Order> findOrdersByCustomer(Person customer);
	List<Order> findOrders(Date startDate, Date endDate,
			String customerFirstName, String customerLastName,String merchantName, String dealName,OrderStatus status , int id);
	
	boolean CheckMinimumOrder(Order order);
	
	Transaction buy(int dealId, int optionId, int quantity) throws DealExpiredException;
	
	int updateStatus(List<Order> orders, OrderStatus status);
}
