package com.kalatag.service.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("smsNotificationListener")
public class SmsNotificationListener {
	
private Logger logger = LoggerFactory.getLogger(SmsNotificationListener.class);
	
	public void onMessage(String message) {
		try {
			logger.debug("SMS notification: " + message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
