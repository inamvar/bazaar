package com.kalatag.dao;

import java.util.Date;
import java.util.List;

import com.kalatag.domain.Customer;
import com.kalatag.domain.Deal;
import com.kalatag.domain.Merchant;
import com.kalatag.domain.Order;
import com.kalatag.domain.Person;

public interface OrderDao extends GenericDao<Order>{

	List<Order> findPendingOrders(Deal deal);
	List<Order> findOrdersByDeal(Deal deal);
	List<Order> findOrdersByCustomer(Person customer);
	List<Order> findOrders(Date startDate, Date endDate, String customerFisrtName,String customerLastName, String merchantName, String dealName, int id );
}
