package com.dariksoft.kalatag.service.listener;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

@Service
public class GenericMessageCreator<T> implements MessageCreator{

	private Class<T> type;
	

	public GenericMessageCreator() {
		super();
		Type t = getClass().getGenericSuperclass();
		ParameterizedType pt = (ParameterizedType) t;
		type = (Class) pt.getActualTypeArguments()[0];
	}


	public Class<T> getType() {
		return type;
	}


	public void setType(Class<T> type) {
		this.type = type;
	}


	public GenericMessageCreator(Class<T> type) {
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
