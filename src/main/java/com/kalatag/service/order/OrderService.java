package com.kalatag.service.order;

import java.util.Date;
import java.util.List;

import com.kalatag.domain.Customer;
import com.kalatag.domain.Deal;
import com.kalatag.domain.Merchant;
import com.kalatag.domain.Order;
import com.kalatag.domain.Person;
import com.kalatag.domain.Transaction;
import com.kalatag.exception.DealExpiredException;
import com.kalatag.service.CRUDService;

public interface OrderService extends CRUDService<Order>{

	List<Order> confirmOrder(Order order);
	List<Order> findPendingOrders(Deal deal);
	List<Order> findOrdersByDeal(Deal deal);
	List<Order> findOrdersByCustomer(Person customer);
	List<Order> findOrders(Date startDate, Date endDate,
			String customerFirstName, String customerLastName,String merchantName, String dealName, int id);
	
	boolean CheckMinimumOrder(Order order);
	
	Transaction buy(int dealId, int optionId, int quantity) throws DealExpiredException;
	
	
}
