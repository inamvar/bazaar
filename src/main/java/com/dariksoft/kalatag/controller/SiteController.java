package com.dariksoft.kalatag.controller;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.dariksoft.kalatag.domain.Deal;
import com.dariksoft.kalatag.domain.DealOption;
import com.dariksoft.kalatag.domain.ItemStatus;
import com.dariksoft.kalatag.domain.Order;
import com.dariksoft.kalatag.domain.Person;
import com.dariksoft.kalatag.service.DealOptionService;
import com.dariksoft.kalatag.service.DealService;
import com.dariksoft.kalatag.service.ItemCategoryService;
import com.dariksoft.kalatag.service.order.OrderService;
import com.dariksoft.kalatag.service.person.PersonService;
import com.dariksoft.kalatag.util.Util;

@Controller
@RequestMapping(value = "/")
public class SiteController {
	private static final Logger logger = LoggerFactory
			.getLogger(SiteController.class);

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private PersonService personService;
	
	@Autowired
	private OrderService orderService ;
	
	@Autowired
	private DealService dealService ;
	
	@Autowired
	private ItemCategoryService categoryService ;
	
	@Autowired
	private DealOptionService optionService ;

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		//logger.info("Welcome to website! The client locale is {}.", locale);

		model.addAttribute("title",
				messageSource.getMessage("website.home.title", null, locale));
		model.addAttribute("categories", categoryService.findAll() );
		model.addAttribute("deals", dealService.findDealsByStatus(ItemStatus.ON));
		return "website/index";
	}

	@RequestMapping(value = "/buy", method = RequestMethod.GET)
	public String newOrder(@RequestParam("dealId") int dealId,
			@RequestParam("optionId") int optionId,
			@RequestParam("qty") int qty, Locale locale, Model uiModel) {
		logger.info("---------new order---------");
		Deal deal = dealService.find(dealId);
		logger.info("deal= "+ deal.getId() + ", " + deal.getName());
		DealOption option = optionService.find(optionId);
		logger.info("option= "+ option.getId() +", " + option.getName());
		logger.info("username= "+ Util.getCurrentUserName());
		Person customer = personService.findByUserName(Util.getCurrentUserName());
		logger.info("customer= "+ customer.getId() +", " + customer.getFirstName() +" " +customer.getLastName());
		Order order =new Order(deal, option, customer, qty);
		order = orderService.create(order);
		uiModel.addAttribute("order",order);
		return "website/orderconfirm";
	}

	@RequestMapping(value = "/myerror", method = RequestMethod.GET)
	public String myerror() throws Throwable {
		throw new Exception("This is a sample exception.");

	}
	
	@RequestMapping(value = "/changepassword", method = RequestMethod.GET)
	public String changePassword(Model uiModel) throws Throwable {
		
		return "website/changePassword";
	}
	
	@RequestMapping(value = "/changepassword", method = RequestMethod.POST)
	public String changePassword(@RequestParam("new_password") String newPassword, Model uiModel, Locale locale) throws Throwable {
		
		Person person = personService.findByUserName(Util.getCurrentUserName());
		if(person !=null && person.getId() > 0){
			int result = personService.changePassword(person.getId(), newPassword);
			if(result > 0)
				uiModel.addAttribute("successMsg",messageSource.getMessage("security.password.change.success",null, locale));
		}
		return "website/index";

	}
}
