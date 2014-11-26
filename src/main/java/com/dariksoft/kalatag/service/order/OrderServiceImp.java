package com.dariksoft.kalatag.service.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.dariksoft.kalatag.domain.Order;
import com.dariksoft.kalatag.service.CRUDServiceImp;

@Service
public class OrderServiceImp extends CRUDServiceImp<Order> implements OrderService{
	
	@Autowired
	JmsTemplate template;
	
	public void notifyOrderConfirmation(Order order){
		template.send(new OrderMessageCreator(order));
	}
}
