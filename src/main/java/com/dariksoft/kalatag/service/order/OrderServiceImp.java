package com.dariksoft.kalatag.service.order;

import javax.jms.Destination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dariksoft.kalatag.dao.GenericDao;
import com.dariksoft.kalatag.domain.Order;
import com.dariksoft.kalatag.service.CRUDServiceImp;
import com.dariksoft.kalatag.service.listener.GenericMessageCreator;

@Service
public class OrderServiceImp extends CRUDServiceImp<Order> implements OrderService{
	
	@Autowired
	JmsTemplate template;
	
	@Autowired
	GenericDao<Order> dao;
	
	
	@Autowired
	Destination orderConfirmation;
	
//	@Autowired
//	GenericMessageCreator<Order> messageCreator;
	
	@Override
	@Transactional
	public Order create(Order o) {
		Order order = dao.create(o);
		template.setDefaultDestination(orderConfirmation);
		MessageCreator messageCreator = new GenericMessageCreator<Order>(order);
		template.send(messageCreator);
		return order;
	}
}
