package com.kalatag.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.kalatag.domain.Deal;
import com.kalatag.domain.Order;
import com.kalatag.service.DealService;
import com.kalatag.service.order.OrderService;

@Controller
@RequestMapping(value = "/admin/order")
public class OrderController {

	private static final Logger logger = LoggerFactory
			.getLogger(OrderController.class);

	@Autowired
	OrderService orderService;

	@Autowired
	DealService dealService;

	@Autowired
	private MessageSource messageSource;

	@RequestMapping(method = RequestMethod.GET)
	public String list(
			@RequestParam(value = "deal", required = false) Integer dealId,
			@RequestParam(value = "msg", required = false) String msg,
			Locale locale, Model uiModel) {

		if (dealId != null && dealId > 0) {

			Deal deal = dealService.find(dealId);
			uiModel.addAttribute("orders",
					deal != null ? orderService.findOrdersByDeal(deal)
							: new ArrayList<Order>());
		} 
/*		else {
			uiModel.addAttribute("orders", orderService.findAll());
		}*/

		if (msg != null) {
			uiModel.addAttribute("successMsg", msg);
		}
		uiModel.addAttribute("title",
				messageSource.getMessage("admin.menu.orders", null, locale));
		return "order/list";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String list(@RequestParam(required = false, value="startDate") String startDateString,
			@RequestParam(required = false, value= "endDate") String endDateString,
			@RequestParam("customer_firstname") String customerFirstName,
			@RequestParam("customer_lastname") String customerLastName,
			@RequestParam("merchant_name") String merchantName,
			@RequestParam("deal_name") String dealName,
			@RequestParam("order_id") Integer orderId,
			Locale locale, Model uiModel) {

		Date startDate = null;
		Date endDate =null;
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd", Locale.ENGLISH);
		if(startDateString != null && !startDateString.isEmpty()){
			try {
				startDate = df.parse(startDateString);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		if(endDateString != null && !endDateString.isEmpty()){
			try {
				endDate = df.parse(endDateString);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		List<Order> orders = orderService.findOrders(startDate, endDate, customerFirstName, customerLastName, merchantName, dealName, orderId ==null ? 0 : orderId);
		uiModel.addAttribute("orders",orders);
		uiModel.addAttribute("title",
				messageSource.getMessage("admin.menu.orders", null, locale));
		return "order/list";
	}


	
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public String showOrderDetail(
			@RequestParam(value = "id", required = true) Integer id,
			Locale locale, Model uiModel) {

		Order order =orderService.find(id);
		Hibernate.initialize(order.getCoupons());
		uiModel.addAttribute("order",order);

		return "order/detail";
	}
	
	

	@RequestMapping(value = "/delete/{id}")
	public String delete(@PathVariable int id) {

		orderService.delete(id);

		return "redirect:/admin/order";
	}

}
