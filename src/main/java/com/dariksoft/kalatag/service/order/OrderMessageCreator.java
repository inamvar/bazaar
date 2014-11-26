package com.dariksoft.kalatag.service.order;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.springframework.jms.core.MessageCreator;

import com.dariksoft.kalatag.domain.Order;

public class OrderMessageCreator implements MessageCreator{

	Order order;
	
	
	
	public OrderMessageCreator(Order order) {
		super();
		this.order = order;
	}


	@Override
	public Message createMessage(Session session) throws JMSException {
		ObjectMessage message;

		try {
			message = session.createObjectMessage();
			message.setObject(order);
			return message;
		} catch (JMSException e) {
			e.printStackTrace();
		}

		return null;
	
	}

}
