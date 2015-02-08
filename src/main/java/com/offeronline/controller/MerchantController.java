package com.offeronline.controller;


import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.offeronline.domain.City;
import com.offeronline.domain.Contact;
import com.offeronline.domain.Merchant;
import com.offeronline.domain.MerchantPayment;
import com.offeronline.domain.Person;
import com.offeronline.propertyeditor.CityEditor;
import com.offeronline.service.CityService;
import com.offeronline.service.MerchantPaymentService;
import com.offeronline.service.merchant.MerchantService;

@Controller
@RequestMapping(value = "/admin/merchant")
public class MerchantController {

	private static final Logger logger = LoggerFactory
			.getLogger(MerchantController.class);
	@Autowired
	MerchantService merchantService;

	@Autowired
	CityService cityService;

	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private MerchantPaymentService mpService ;

	private @Autowired CityEditor cityEditor;

	@InitBinder
	public void iniBinder(WebDataBinder binder) {
		binder.registerCustomEditor(City.class, this.cityEditor);
	}

	@RequestMapping(method = RequestMethod.GET)
	public String list(Locale locale, Model uiModel) {

		uiModel.addAttribute("merchants", merchantService.findAll());
		uiModel.addAttribute("title",
				messageSource.getMessage("admin.menu.merchants", null, locale));
		return "merchant/list";
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addForm(Locale locale, Model uiModel) {

		uiModel.addAttribute("title", messageSource.getMessage(
				"merchant.insert.message", null, locale));
		Merchant merch = new Merchant();

		merch.setContactPoint(new Person());
		Contact contact = new Contact();
		City city = new City();
		contact.setCity(city);
		merch.setContact(contact);

		uiModel.addAttribute("merchant", merch);
		uiModel.addAttribute("cities", cityService.findAll());

		return "merchant/add";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(@ModelAttribute("merchant") @Valid Merchant merchant,
			BindingResult result, Locale locale, Model uiModel) {

		if (result.hasErrors()) {
			uiModel.addAttribute("title", messageSource.getMessage(
					"merchant.insert.message", null, locale));
			uiModel.addAttribute("cities", cityService.findAll());
			return "merchant/add";
		}
		merchantService.create(merchant);

		return "redirect:/admin/merchant";
	}
	
	
	@RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
	public String add(@PathVariable Integer id,
		 Locale locale, Model uiModel) {

		Merchant merchant = merchantService.find(id);
		uiModel.addAttribute("merchant", merchant);
		return "merchant/detail";
	}
	
	
	@RequestMapping(value = "/paymentList/{merchantId}", method = RequestMethod.GET)
	public String paymentList(@PathVariable Integer merchantId, Locale locale, Model uiModel){
		
		Merchant merchant = merchantService.find(merchantId);
		List<MerchantPayment> payments = mpService.findByMerchant(merchant);
		uiModel.addAttribute("payments", payments);
		uiModel.addAttribute("merchant", merchant);
		return "merchant/paymentList";
	}
	
	@RequestMapping(value = "/insertPayment/{merchantId}", method = RequestMethod.GET)
	public String insertPayment(@PathVariable Integer merchantId, Locale locale, Model uiModel){
		
		
		Merchant merchant = merchantService.find(merchantId);
		MerchantPayment payment = new MerchantPayment();
		payment.setMerchant(merchant);
		uiModel.addAttribute("merchant", merchant);
		uiModel.addAttribute("payment", payment);
		return "merchant/insertPayment";
	}
	
	@RequestMapping(value = "/deletePayment/{merchantId}/{paymentId}", method = RequestMethod.GET)
	public String deletePayment(@PathVariable Integer merchantId, @PathVariable Integer paymentId, Locale locale, Model uiModel){
		
		
		mpService.delete(paymentId);
		
		Merchant merchant = merchantService.find(merchantId);
		List<MerchantPayment> payments = mpService.findByMerchant(merchant);
		uiModel.addAttribute("payments", payments);
		uiModel.addAttribute("merchant", merchant);
		return "merchant/paymentList";
		
		
	}
	
	
	
	@RequestMapping(value = "/insertPayment/{merchantId}", method = RequestMethod.POST)
	public String insertPayment(@PathVariable Integer merchantId, @ModelAttribute("payment") @Valid MerchantPayment payment,
			BindingResult result, Locale locale, Model uiModel) {

		if (result.hasErrors()) {
			uiModel.addAttribute("title", messageSource.getMessage(
					"merchant.insert.message", null, locale));
			
			return "merchant/insertPayment";
		}
		payment.setIssueDate(new java.util.Date());
		Merchant merchant = merchantService.find(merchantId);
		payment.setMerchant(merchant);
		uiModel.addAttribute("merchant", merchant);
		mpService.create(payment);
		
		List<MerchantPayment> payments = mpService.findByMerchant(merchant);
		uiModel.addAttribute("payments", payments);
		
		return "merchant/paymentList";
	}


	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public String updateFrom(@PathVariable Integer id, Locale locale,
			Model uiModel) {

		Merchant merchant = merchantService.find(id);
		logger.debug("get person id =" + merchant.getContactPoint().getId());
		logger.debug("get contact id =" + merchant.getContact().getId());
		uiModel.addAttribute("merchant", merchant);
		uiModel.addAttribute("title", messageSource.getMessage(
				"merchant.update.message", null, locale));
		uiModel.addAttribute("cities", cityService.findAll());
		return "merchant/update";
	}

	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	public String update(@ModelAttribute("merchant") @Valid Merchant merchant,
			@PathVariable Integer id, BindingResult result, Locale locale,
			Model uiModel) {
		logger.debug("id =" + merchant.getId());
		logger.debug("person id =" + merchant.getContactPoint().getId());
		logger.debug("contact id =" + merchant.getContact().getId());
		logger.debug("contact email =" + merchant.getContact().getEmail());
		logger.debug("merchant username =" + merchant.getContactPoint().getUsername());
		logger.debug("merchant role =" + merchant.getContactPoint().getPersonRole().getRole());
		if (result.hasErrors()) {
			uiModel.addAttribute("title", messageSource.getMessage(
					"merchant.update.message", null, locale));
			uiModel.addAttribute("cities", cityService.findAll());
			return "merchant/update/" + id;
		}

		merchantService.update(merchant);

		return "redirect:/admin/merchant";
	}

	@RequestMapping(value = "/delete/{id}")
	public String delete(@PathVariable int id) {

		merchantService.delete(id);

		return "redirect:/admin/merchant";
	}

}
