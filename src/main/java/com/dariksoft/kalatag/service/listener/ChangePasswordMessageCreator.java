package com.dariksoft.kalatag.service.listener;

import java.io.Serializable;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import com.dariksoft.kalatag.domain.Person;
import com.dariksoft.kalatag.service.person.PersonService;

@Service
public class ChangePasswordMessageCreator implements MessageCreator {

	@Autowired
	PersonService personService;
	
	private Person person;
	private boolean isReset;
	
	public ChangePasswordMessageCreator(){
		super();
		this.person = null;
		this.isReset = false;
				
	}
	
	public ChangePasswordMessageCreator(int id, boolean isReset) {
		super();
		this.person = personService.find(id);
		this.isReset = isReset;
	}

	@Override
	public Message createMessage(Session session) throws JMSException {

		ObjectMessage message;
		try {
			message = session.createObjectMessage();
			message.setObject((Serializable) person);
			
			return message;
		} catch (JMSException e) {
			e.printStackTrace();
		}
		return null;
	}

}
