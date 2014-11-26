package com.dariksoft.kalatag.service.person;

import javax.jms.Destination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dariksoft.kalatag.dao.GenericDao;
import com.dariksoft.kalatag.domain.Person;
import com.dariksoft.kalatag.service.CRUDServiceImp;

@Service
public class PersonServiceImp  extends CRUDServiceImp<Person> implements PersonService {
	
	@Autowired
	JmsTemplate template;
	
	@Autowired
	GenericDao<Person> genericDao;
	
	
	@Autowired
	Destination registration;
	
	@Override
	@Transactional
	public Person create(Person p) {
		Person person = genericDao.create(p);
		template.setDefaultDestination(registration);
		template.send(new PersonMessageCreator(person));
		return person;
	}
	
	
	
	public JmsTemplate getTemplate() {
		return template;
	}


	public void setTemplate(JmsTemplate template) {
		this.template = template;
	}


	public GenericDao<Person> getGenericDao() {
		return genericDao;
	}


	public void setGenericDao(GenericDao<Person> genericDao) {
		this.genericDao = genericDao;
	}


	public Destination getRegistration() {
		return registration;
	}


	public void setRegistration(Destination registration) {
		this.registration = registration;
	}


	
	

}
