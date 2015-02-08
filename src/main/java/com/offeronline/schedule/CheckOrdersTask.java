package com.offeronline.schedule;

import java.util.ArrayList;
import java.util.List;

import javax.jms.Destination;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.offeronline.domain.Order;
import com.offeronline.domain.OrderStatus;
import com.offeronline.service.DealService;
import com.offeronline.service.listener.GenericMessageCreator;
import com.offeronline.service.order.OrderService;


public class CheckOrdersTask {

	private static final Logger logger = LoggerFactory
			.getLogger(CheckOrdersTask.class);

	@Autowired
	JmsTemplate template;

	@Autowired
	Destination canceledOrdersNotification;

	@Autowired
	OrderService orderService;

	@Autowired
	DealService dealService;

	// @Scheduled(cron = "0 0 03 * * ?")
	//@Scheduled(fixedRate = 5000)
	public void checkForCanceledOrders() {
	
		logger.info("Running scheduled Task. checking for canceld orders... ");

		try {
			List<Order> orders = orderService.findOrders(null, null, null,
					null, null, null, OrderStatus.PENDING, 0);

			for (Order order : orders) {
				boolean isReached = orderService.CheckMinimumOrder(order);
				/*if (isReached) {*/
					/*order.setStatus(OrderStatus.ClOSED);
					List<Order> closedOrders = new ArrayList<Order>();
					closedOrders.add(order);
					orderService.updateStatus(closedOrders, OrderStatus.ClOSED);
					logger.info(String.format(
							"Order number {0} closed. ", order.getId()));*/
				//} else {
				if (!isReached){
					if (dealService.isExpired(order.getDeal())) {
						order.setStatus(OrderStatus.CANCELED);
						List<Order> canceledOrders = new ArrayList<Order>();
						canceledOrders.add(order);
						orderService.updateStatus(canceledOrders,
								OrderStatus.CANCELED);
						template.setDefaultDestination(canceledOrdersNotification);
						MessageCreator messageCreator = new GenericMessageCreator<Order>(
								order);
						template.send(messageCreator);
						logger.info(String.format(
								"Order number {0} canceled. ", order.getId()));
					}

				}

			}

			logger.info(" Finishing scheduled Task: canceled ordered checked ");
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

	}

}
