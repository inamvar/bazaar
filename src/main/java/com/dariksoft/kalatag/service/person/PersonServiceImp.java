package com.dariksoft.kalatag.service.person;

import javax.jms.Destination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dariksoft.kalatag.dao.GenericDao;
import com.dariksoft.kalatag.domain.Person;
import com.dariksoft.kalatag.service.CRUDServiceImp;
import com.dariksoft.kalatag.service.listener.GenericMessageCreator;

@Service
public class PersonServiceImp  extends CRUDServiceImp<Person> implements PersonService {
	
	@Autowired
	JmsTemplate template;
	
	@Autowired
	GenericDao<Person> dao;
	
	
	@Autowired
	Destination registration;
	
	
	@Override
	@Transactional
	public Person create(Person p) {
		try{
			Person person = dao.create(p);
			template.setDefaultDestination(registration);
			MessageCreator messageCreator = new GenericMessageCreator<Person>(person);
			template.send(messageCreator);
			return person;
		}catch(Exception e){
			return null;
		}
	}

	
}
