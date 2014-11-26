package com.dariksoft.kalatag.service.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("auditingListener")
public class AuditingListener {
	
	private Logger logger = LoggerFactory.getLogger(AuditingListener.class);
	
	public void onMessage(String message) {
		try {
			logger.info("Auditing: " + message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
