package com.dariksoft.kalatag.dao;

import java.util.List;

import com.dariksoft.kalatag.domain.Deal;
import com.dariksoft.kalatag.domain.Order;

public interface OrderDao extends GenericDao<Order>{

	List<Order> findPendingOrders(Deal deal);
}
