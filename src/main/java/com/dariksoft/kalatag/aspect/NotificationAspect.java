package com.dariksoft.kalatag.aspect;

import javax.jms.Destination;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import com.dariksoft.kalatag.domain.Merchant;
import com.dariksoft.kalatag.service.listener.GenericMessageCreator;

@Aspect
public class NotificationAspect {
	
	@Autowired
	JmsTemplate template;
	
	@Autowired
	Destination auditing;
	
	@After("execution(* com.dariksoft.kalatag.service.merchant.MerchantServiceImp.create(..))")
	public void afterMerchantCreate(JoinPoint jp){
		template.setDefaultDestination(auditing);
		Object[] args = jp.getArgs();
		
		MessageCreator messageCreator = new GenericMessageCreator<String>(((Merchant)args[0]).getName() + " created!");
		template.send(messageCreator);
	}

}
