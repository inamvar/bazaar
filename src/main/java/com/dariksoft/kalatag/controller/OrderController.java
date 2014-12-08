package com.dariksoft.kalatag.controller;

import java.util.ArrayList;
import java.util.Locale;

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

import com.dariksoft.kalatag.domain.Deal;
import com.dariksoft.kalatag.domain.Order;
import com.dariksoft.kalatag.service.DealService;
import com.dariksoft.kalatag.service.order.OrderService;

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
		} else {
			uiModel.addAttribute("orders", orderService.findAll());
		}

		if (msg != null) {
			uiModel.addAttribute("successMsg", msg);
		}
		uiModel.addAttribute("title",
				messageSource.getMessage("admin.menu.orders", null, locale));
		return "order/list";
	}
	
	@RequestMapping(value = "/admin/order/filter", method = RequestMethod.GET)
	public String getBetweenDate(
			@RequestParam(value = "fromDate", required = true) String fromDate,
			@RequestParam(value = "toDate", required = true) String toDate,
			Locale locale, Model uiModel) {

		uiModel.addAttribute("title",
				messageSource.getMessage("admin.menu.orders", null, locale));
		return "order/list";
	}
	
	

	@RequestMapping(value = "/delete/{id}")
	public String delete(@PathVariable int id) {

		orderService.delete(id);

		return "redirect:/admin/order";
	}

}
