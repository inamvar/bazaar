package com.dariksoft.kalatag.service.listener;

import java.io.Serializable;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

@Service
public class GenericMessageCreator<T> implements MessageCreator{

	T type;
	
	
	public T getType() {
		return type;
	}


	public void setType(T type) {
		this.type = type;
	}


	public GenericMessageCreator(T type) {
		super();
		this.type = type;
	}


	@Override
	public Message createMessage(Session session)  {
		ObjectMessage message;
		try {
			message = session.createObjectMessage();
			message.setObject((Serializable) type);
			return message;
		} catch (JMSException e) {
			e.printStackTrace();
		}

		return null;
	}
}
