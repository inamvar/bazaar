package com.dariksoft.kalatag.controller;

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

import com.dariksoft.kalatag.domain.City;
import com.dariksoft.kalatag.domain.Contact;
import com.dariksoft.kalatag.domain.Merchant;
import com.dariksoft.kalatag.domain.Person;
import com.dariksoft.kalatag.propertyeditor.CityEditor;
import com.dariksoft.kalatag.service.CityService;
import com.dariksoft.kalatag.service.MerchantService;

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

	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public String updateFrom(@PathVariable Integer id, Locale locale,
			Model uiModel) {

		Merchant merchant = merchantService.find(id);
		logger.info("get person id =" + merchant.getContactPoint().getId());
		logger.info("get contact id =" + merchant.getContact().getId());
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
		logger.info("id =" + merchant.getId());
		logger.info("person id =" + merchant.getContactPoint().getId());
		logger.info("contact id =" + merchant.getContact().getId());
		logger.info("contact email =" + merchant.getContact().getEmail());
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
