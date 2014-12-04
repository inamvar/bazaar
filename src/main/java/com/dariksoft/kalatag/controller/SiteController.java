package com.dariksoft.kalatag.controller;

import java.util.ArrayList;
import java.util.Locale;

import javax.validation.Valid;

import org.apache.velocity.exception.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.dariksoft.kalatag.domain.City;
import com.dariksoft.kalatag.domain.Contact;
import com.dariksoft.kalatag.domain.Customer;
import com.dariksoft.kalatag.domain.Deal;
import com.dariksoft.kalatag.domain.DealLabel;
import com.dariksoft.kalatag.domain.DealOption;
import com.dariksoft.kalatag.domain.ItemCategory;
import com.dariksoft.kalatag.domain.ItemStatus;
import com.dariksoft.kalatag.domain.Order;
import com.dariksoft.kalatag.domain.Person;
import com.dariksoft.kalatag.propertyeditor.CityEditor;
import com.dariksoft.kalatag.service.CityService;
import com.dariksoft.kalatag.service.CustomerService;
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
	private OrderService orderService;

	@Autowired
	private DealService dealService;

	@Autowired
	private ItemCategoryService categoryService;

	@Autowired
	private DealOptionService optionService;
	
	@Autowired
	private CustomerService customerService;
	
	
	@Autowired
	private CityService cityService;
	
	private @Autowired CityEditor cityEditor;

	@InitBinder
	public void iniBinder(WebDataBinder binder) {
		binder.registerCustomEditor(City.class, this.cityEditor);
	}


	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(
			@RequestParam(value = "category", required = false) Integer catergoryId,
			Locale locale, Model model) {
		
		model.addAttribute("title",
				messageSource.getMessage("website.home.title", null, locale));
		model.addAttribute("categories", categoryService.findAll());
		model.addAttribute("featureds", dealService.findDealsByLabelAndStatus(
				DealLabel.FEATURED, ItemStatus.ON));
		if (catergoryId !=null && catergoryId > 0) {
			ItemCategory category = categoryService.find(catergoryId);
			if (category != null){
				model.addAttribute("deals", dealService
						.findDealsByCategoryAndStatusAndNotLabel(category,
								DealLabel.FEATURED, ItemStatus.ON));
				}else{
					model.addAttribute("deals", new ArrayList<Deal>());
				}
			
		} else {
			model.addAttribute("deals", dealService
					.findDealsByStatusAndNotLabel(DealLabel.FEATURED,
							ItemStatus.ON));
		}
		return "website/index";
	}
	
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public String detail(@RequestParam("deal") int dealId, Locale locale , Model uiModel){
		Deal deal = dealService.find(dealId);
		if(deal == null)
			  throw new ResourceNotFoundException(dealId +"");
		else{
			uiModel.addAttribute("deal", deal);
		}
		
		uiModel.addAttribute("title", messageSource.getMessage(
				"website.detail.title", null, locale));
		return "website/detail";
	}
	

	@RequestMapping(value = "/buy", method = RequestMethod.GET)
	public String newOrder(@RequestParam("dealId") int dealId,
			@RequestParam("optionId") int optionId,
			@RequestParam("qty") int qty, Locale locale, Model uiModel) {
		logger.info("---------new order---------");
		Deal deal = dealService.find(dealId);
		logger.info("deal= " + deal.getId() + ", " + deal.getName());
		DealOption option = optionService.find(optionId);
		logger.info("option= " + option.getId() + ", " + option.getName());
		logger.info("username= " + Util.getCurrentUserName());
		Person customer = personService.findByUserName(Util
				.getCurrentUserName());
		logger.info("customer= " + customer.getId() + ", "
				+ customer.getFirstName() + " " + customer.getLastName());
		Order order = new Order(deal, option, customer, qty);
		order= orderService.create(order);
		
		uiModel.addAttribute("order", order);
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
	public String changePassword(
			@RequestParam("new_password") String newPassword, Model uiModel,
			Locale locale) throws Throwable {

		Person person = personService.findByUserName(Util.getCurrentUserName());
		if (person != null && person.getId() > 0) {
			int result = personService.changePassword(person.getId(),
					newPassword);
			if (result > 0)
				uiModel.addAttribute("successMsg", messageSource.getMessage(
						"security.password.change.success", null, locale));
		}
		uiModel.addAttribute("title",
				messageSource.getMessage("website.home.title", null, locale));
		uiModel.addAttribute("categories", categoryService.findAll());
		uiModel.addAttribute("featureds", dealService
				.findDealsByLabelAndStatus(DealLabel.FEATURED, ItemStatus.ON));
		uiModel.addAttribute("deals", dealService.findDealsByStatusAndNotLabel(
				DealLabel.FEATURED, ItemStatus.ON));
		return "website/index";

	}
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String RegisterCustomer(Model uiModel, Locale locale){
		
		uiModel.addAttribute("title", messageSource.getMessage(
				"website.register.title", null, locale));
		uiModel.addAttribute("cities", cityService.findAll());
		Customer customer = new Customer();
		customer.setContact(new Contact());
		uiModel.addAttribute("customer",customer);
		return "website/register";
	}
	
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String RegisterCustomerPost(@ModelAttribute("customer") @Valid Customer customer,
			BindingResult result, Locale locale, Model uiModel) {

		if (result.hasErrors()) {
			uiModel.addAttribute("title", messageSource.getMessage(
					"website.register.title", null, locale));
			uiModel.addAttribute("cities", cityService.findAll());
			return "website/register";
		}
		customer = customerService.create(customer);
		if(customer !=null && customer.getId() > 0){
			uiModel.addAttribute("msg", messageSource.getMessage(
					"customer.register.success", null, locale) );
		}else{
			
		}
			
		return "website/index";
	}
}
