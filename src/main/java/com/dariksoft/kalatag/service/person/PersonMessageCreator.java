package com.dariksoft.kalatag.service.person;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.springframework.jms.core.MessageCreator;

import com.dariksoft.kalatag.domain.Person;

public class PersonMessageCreator implements MessageCreator {

	Person person;
	
	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public PersonMessageCreator(Person person) {
		super();
		this.person = person;
	}

	@Override
	public Message createMessage(Session session) throws JMSException {
		ObjectMessage message;

		try {
			message = session.createObjectMessage();
			message.setObject(person);
			return message;
		} catch (JMSException e) {
			e.printStackTrace();
		}

		return null;
	}

}
