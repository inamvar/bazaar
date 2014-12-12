package com.kalatag.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.kalatag.domain.City;
import com.kalatag.domain.Contact;
import com.kalatag.domain.Customer;
import com.kalatag.domain.Deal;
import com.kalatag.domain.DealLabel;
import com.kalatag.domain.DealOption;
import com.kalatag.domain.ItemCategory;
import com.kalatag.domain.ItemStatus;
import com.kalatag.domain.Person;
import com.kalatag.domain.Transaction;
import com.kalatag.domain.TransactiontStatus;
import com.kalatag.exception.DealExpiredException;
import com.kalatag.gateway.PaymentGateway;
import com.kalatag.propertyeditor.CityEditor;
import com.kalatag.service.CityService;
import com.kalatag.service.CustomerService;
import com.kalatag.service.DealOptionService;
import com.kalatag.service.DealService;
import com.kalatag.service.ItemCategoryService;
import com.kalatag.service.accounting.TransactionService;
import com.kalatag.service.order.OrderService;
import com.kalatag.service.person.PersonService;
import com.kalatag.util.Util;

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

	@Autowired
	private PaymentGateway paymentGateway;

	@Autowired
	TransactionService txnService;

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
		if (catergoryId != null && catergoryId > 0) {
			ItemCategory category = categoryService.find(catergoryId);
			if (category != null) {
				model.addAttribute("deals", dealService
						.findDealsByCategoryAndStatusAndNotLabel(category,
								DealLabel.FEATURED, ItemStatus.ON));
			} else {
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
	public String detail(@RequestParam("deal") int dealId, Locale locale,
			Model uiModel) {
		Deal deal = dealService.find(dealId);
		boolean expired = false;
		if (deal == null)
			throw new ResourceNotFoundException(dealId + "");
		else {
			uiModel.addAttribute("deal", deal);

			Date now = new Date();
			if ((deal.getValidity().compareTo(now) < 0)
					|| deal.getStatus() != ItemStatus.ON) {
				expired = true;
			}

		}
		logger.debug("deal is Expired:" + expired);
		uiModel.addAttribute("expired", expired);
		uiModel.addAttribute("title",
				messageSource.getMessage("website.detail.title", null, locale));
		return "website/detail";
	}

	@RequestMapping(value = "/buy", method = RequestMethod.POST)
	public String newOrder(@RequestParam("dealId") int dealId,
			@RequestParam("optionId") int optionId,
			@RequestParam("qty") int qty, Locale locale, Model uiModel)
			throws DealExpiredException {
		logger.debug("---------new order---------");
		Deal deal = dealService.find(dealId);
		logger.debug("deal= " + deal.getId() + ", " + deal.getName());
		DealOption option = optionService.find(optionId);
		logger.debug("option= " + option.getId() + ", " + option.getName());
		logger.debug("username= " + Util.getCurrentUserName());
		Person customer = personService.findByUserName(Util
				.getCurrentUserName());
		logger.debug("customer= " + customer.getId() + ", "
				+ customer.getFirstName() + " " + customer.getLastName());
		Date now = new Date();
		if ((deal.getValidity().compareTo(now) < 0)
				|| deal.getStatus() != ItemStatus.ON) {
			throw new DealExpiredException();
		} else {
			Transaction txn = new Transaction();
			txn.setDate(now);
			double amount = deal.getPrice()
					- ((option.getDiscount() / 100) * deal.getPrice()) * qty;

			txn.setAmount(amount);
			txn.setStatus(TransactiontStatus.PENDING);
			txn.setDealId(dealId);
			txn.setDealOptionId(optionId);
			txn.setQty(qty);
			txn.setPerson(customer);
			txn = txnService.create(txn);
			txn.setReservationNumber(txn.getId() + "");
			logger.debug("reservation number=" + txn.getReservationNumber());
			
			return "redirect:"+ paymentGateway.GetRequestUrl(txn);
		}
		
	}

	@RequestMapping(value = "/myerror", method = RequestMethod.GET)
	public String myerror() throws Throwable {
		throw new Exception("This is a sample exception.");

	}

	@RequestMapping(value = "/resetpass", method = RequestMethod.GET)
	public String resetPassword(Model uiModel) throws Throwable {

		return "website/resetpass";
	}

	@RequestMapping(value = "/resetpass", method = RequestMethod.POST)
	public String resetPassword(@RequestParam("email") String email,
			Model uiModel, Locale locale) throws Throwable {

		Person person = personService.findByUserName(email);
		if (person != null && person.getId() > 0) {
			int result = personService.resetPassword(person.getId(), null);
			if (result > 0)
				uiModel.addAttribute("successMsg", messageSource.getMessage(
						"security.resetpass.success.message", null, locale));
		}
		uiModel = fillModelForIndex(uiModel, locale);
		return "website/index";

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
		uiModel = fillModelForIndex(uiModel, locale);
		return "website/index";

	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String RegisterCustomer(Model uiModel, Locale locale) {

		uiModel.addAttribute("title", messageSource.getMessage(
				"website.register.title", null, locale));
		uiModel.addAttribute("cities", cityService.findAll());
		Customer customer = new Customer();
		customer.setContact(new Contact());
		uiModel.addAttribute("customer", customer);
		return "website/register";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String RegisterCustomerPost(
			@ModelAttribute("customer") @Valid Customer customer,
			BindingResult result, Locale locale, Model uiModel) {

		if (result.hasErrors()) {
			uiModel.addAttribute("title", messageSource.getMessage(
					"website.register.title", null, locale));
			uiModel.addAttribute("cities", cityService.findAll());
			return "website/register";
		}
		customer = customerService.create(customer);
		logger.debug("customer= " + customer.getId() + ", "
				+ customer.getFirstName() + " " + customer.getLastName());
		if (customer != null && customer.getId() > 0) {
			uiModel.addAttribute("successMsg", messageSource.getMessage(
					"customer.register.success", null, locale));
		}

		uiModel = fillModelForIndex(uiModel, locale);

		return "website/index";
	}

	private Model fillModelForIndex(Model uiModel, Locale locale) {
		uiModel.addAttribute("title",
				messageSource.getMessage("website.home.title", null, locale));
		uiModel.addAttribute("categories", categoryService.findAll());
		uiModel.addAttribute("featureds", dealService
				.findDealsByLabelAndStatus(DealLabel.FEATURED, ItemStatus.ON));
		uiModel.addAttribute("deals", dealService.findDealsByStatusAndNotLabel(
				DealLabel.FEATURED, ItemStatus.ON));

		return uiModel;
	}
}
