package com.dariksoft.kalatag.service.order;

import com.dariksoft.kalatag.domain.Customer;
import com.dariksoft.kalatag.domain.Deal;
import com.dariksoft.kalatag.domain.DealOption;
import com.dariksoft.kalatag.domain.Order;
import com.dariksoft.kalatag.service.CRUDService;

public interface OrderService extends CRUDService<Order>{

	Order newOrder(Deal deal,DealOption option, Customer customer, int qty);
}
