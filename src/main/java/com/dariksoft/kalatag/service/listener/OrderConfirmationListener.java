package com.dariksoft.kalatag.service.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.dariksoft.kalatag.domain.Order;

@Component("orderConfirmationListenet")
public class OrderConfirmationListener {

	private Logger logger = LoggerFactory
			.getLogger(OrderConfirmationListener.class);

	public void onMessage(Order order) {
		try {
			logger.info("Order confirmation: id:" + order.getId()
					+ ", Customer: " + order.getPerson().getFirstName() + " "
					+ order.getPerson().getLastName() + ", Status: "
					+ order.getStatus());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
