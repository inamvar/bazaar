package com.dariksoft.kalatag.service.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.dariksoft.kalatag.domain.Person;

@Component("registerationListener")
public class RegisterationListener {
	
	private Logger logger = LoggerFactory.getLogger(RegisterationListener.class);
	
	
	public void onMessage(Person person) {
		try {
			System.out.println("----------------------------------" + person + "------------------");
			
			logger.info("User registeration: " + person);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
