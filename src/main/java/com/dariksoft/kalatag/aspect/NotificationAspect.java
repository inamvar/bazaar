package com.dariksoft.kalatag.aspect;

import javax.jms.Destination;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import com.dariksoft.kalatag.domain.Merchant;
import com.dariksoft.kalatag.domain.Person;
import com.dariksoft.kalatag.service.listener.GenericMessageCreator;

@Aspect
public class NotificationAspect {
	
	@Autowired
	JmsTemplate template;
	
	@Autowired
	Destination auditing;
	
	@Autowired
	Destination registration;
	
	@After("within(com.dariksoft.kalatag.service.CRUDService+) && target(com.dariksoft.kalatag.service.merchant.MerchantServiceImp) execution(* create(..))")
	public void afterMerchantCreate(JoinPoint jp){
		template.setDefaultDestination(auditing);
		Object[] args = jp.getArgs();
		
		MessageCreator messageCreator = new GenericMessageCreator<String>(((Merchant)args[0]).getName() + " created!");
		template.send(messageCreator);
	}
	
	
	
	@After("within(com.dariksoft.kalatag.service.CRUDService+) && target(com.dariksoft.kalatag.service.person.PersonServiceImp) && execution(* create(..))")
	public void afterPersonCreate(JoinPoint jp){
	
		Object[] args = jp.getArgs();
		template.setDefaultDestination(registration);
		MessageCreator messageCreator = new GenericMessageCreator<Person>((Person) args[0]);
		template.send(messageCreator);
	}

}
