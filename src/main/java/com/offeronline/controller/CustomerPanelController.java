package com.offeronline.controller;

import static ch.lambdaj.Lambda.sumFrom;

import java.util.List;
import java.util.Locale;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.offeronline.domain.Customer;
import com.offeronline.domain.Deal;
import com.offeronline.domain.Order;
import com.offeronline.service.CouponService;
import com.offeronline.service.CustomerService;
import com.offeronline.service.DealService;
import com.offeronline.service.order.OrderService;
import com.offeronline.service.person.PersonService;
import com.offeronline.util.Util;

@Controller
@RequestMapping(value = "/customer")
public class CustomerPanelController {

	@Autowired
	private MessageSource messageSource;
	@Autowired
	private PersonService personService;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private DealService dealService;
	@Autowired
	private CouponService couponService;
	
	@Autowired
	private OrderService orderService;

	@RequestMapping(value = "/panel", method = RequestMethod.GET)
	public String panel(
			@RequestParam(value = "index", required = false) Integer index,
			Locale locale, Model uiModel) {

		uiModel = fillModel(uiModel, locale);

		if (index == null)
			index = 0;
		uiModel.addAttribute("idx", index);

		return "website/customer/home";
	}
	
	@RequestMapping(value = "/order/detail", method = RequestMethod.GET)
	public String showOrderDetail(
			@RequestParam(value = "id", required = true) Integer id,
			Locale locale, Model uiModel) {

		
	//	uiModel = fillModel(uiModel, locale);
		Order order =orderService.find(id);
		Hibernate.initialize(order.getCoupons());
		uiModel.addAttribute("order",order);
		List<Deal> similars = dealService.findSimilars(order.getDeal());
		uiModel.addAttribute("similars",similars);
		return "website/customer/order/detail";
	}
	
	

	private Model fillModel(Model uiModel, Locale locale) {

		String username = Util.getCurrentUserName();
		if (username != null) {
			
				Customer customer = customerService.findByUserName(username);
				List<Order> orders = orderService.findOrdersByCustomer(customer);
				double totalOrdersPrice = 0;
				if(orders !=null && orders.size() > 0)
					totalOrdersPrice= sumFrom(orders).getTotalPrice();
				uiModel.addAttribute("totalOrdersPrice", totalOrdersPrice);
				uiModel.addAttribute("orders", orders);
				uiModel.addAttribute("customer", customer);	
		}

		uiModel.addAttribute("title", messageSource.getMessage(
				"website.customer.panel.title", null, locale));

		return uiModel;
	}

}
