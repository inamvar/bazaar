package com.dariksoft.kalatag.aspect;

import javax.jms.Destination;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import com.dariksoft.kalatag.domain.Merchant;
import com.dariksoft.kalatag.domain.Person;
import com.dariksoft.kalatag.service.listener.GenericMessageCreator;
import com.dariksoft.kalatag.util.Util;

@Aspect
public class NotificationAspect {
	
	@Autowired
	JmsTemplate template;
	
	@Autowired
	Destination auditing;
	
	@Autowired
	Destination registration;
	
	@Around("within(com.dariksoft.kalatag.service.CRUDService+) && target(com.dariksoft.kalatag.service.merchant.MerchantServiceImp) execution(* create(..))")
	public void aroundMerchantCreate(ProceedingJoinPoint pjp) throws Throwable{
		
		Object[] args = pjp.getArgs();
		Merchant merchant = (Merchant) args[0];
		String password = Util.generateRandomPassword();
		String encryptedPassword = Util.toSHA256(password);
		Person person = merchant.getContactPoint();
		person.setPassword(encryptedPassword);
		try{
			pjp.proceed(new Object[]{merchant});
			person.setPassword(password);	
			template.setDefaultDestination(registration);
			MessageCreator messageCreator = new GenericMessageCreator<Person>(person);
			template.send(messageCreator);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	@Around("within(com.dariksoft.kalatag.service.CRUDService+) && target(com.dariksoft.kalatag.service.person.PersonServiceImp) && execution(* create(..))")
	public void aroundPersonCreate(ProceedingJoinPoint pjp) throws Throwable{
		
		Object[] args = pjp.getArgs();
		Person person = (Person) args[0];
		String password = Util.generateRandomPassword();
		String encryptedPassword = Util.toSHA256(password);
		person.setPassword(encryptedPassword);
		try{
			pjp.proceed(new Object[]{person});
			person.setPassword(password);	
			template.setDefaultDestination(registration);
			MessageCreator messageCreator = new GenericMessageCreator<Person>(person);
			template.send(messageCreator);
		}catch(Exception e){
			e.printStackTrace();
		}
		

	}

}
