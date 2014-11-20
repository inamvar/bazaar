package com.dariksoft.bazaar.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.dariksoft.bazaar.domain.City;
import com.dariksoft.bazaar.domain.Contact;
import com.dariksoft.bazaar.domain.Merchant;
import com.dariksoft.bazaar.domain.Person;
import com.dariksoft.bazaar.service.CityService;
import com.dariksoft.bazaar.service.MerchantService;

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

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView list(Locale locale) {
		Map<String, Object> model = new HashMap<String, Object>();

		model.put("merchants", merchantService.findAll());
		model.put("title",
				messageSource.getMessage("admin.menu.merchants", null, locale));
		return new ModelAndView("merchant/list", model);
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView addForm(@ModelAttribute("command") Merchant merchant,
			BindingResult result, Locale locale) {

		ModelAndView mv = new ModelAndView("merchant/add");
		mv.addObject("title", messageSource.getMessage(
				"merchant.insert.message", null, locale));
		Merchant merch = new Merchant();

		merch.setContactPoint(new Person());
		Contact contact  =new Contact();
		merch.setContact(contact);

		mv.addObject("merchant", merch);
		mv.addObject("cities", cityService.findAll());

		return mv;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(@ModelAttribute("command") Merchant merchant,
			BindingResult result, Locale locale) {
		Map<String, Object> model = new HashMap<String, Object>();

		logger.warn(merchant.getContactPoint().getFirstName());
		logger.warn(merchant.getContactPoint().getLastName());
		logger.warn(merchant.getContactPoint().getBirthday().toString());

		if (result.hasErrors()) {
			List<ObjectError> errors = result.getAllErrors();
			for (ObjectError error : errors) {
				logger.warn(error.toString());
			}

			model.put("errors", result.getAllErrors());
			model.put("title", messageSource.getMessage(
					"merchant.update.message", null, locale));
			return "merchant/add";
		}
		Contact contact = merchant.getContact();
		if (contact != null && contact.getCity() != null) {
			merchant.getContact().setCity(cityService.find(contact.getCity().getId()));
		}
		merchantService.create(merchant);

		return "redirect:/admin/merchant";
	}

	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public ModelAndView updateFrom(@PathVariable Integer id, Locale locale) {
		ModelAndView modelAndView = new ModelAndView("merchant/update");
		Merchant merchant = merchantService.find(id);
		modelAndView.addObject("merchant", merchant);
		modelAndView.addObject("title", messageSource.getMessage(
				"merchant.update.message", null, locale));
		return modelAndView;
	}

	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	public String update(@ModelAttribute("command") Merchant merchant,
			@PathVariable Integer id) {

		merchantService.update(merchant);

		return "redirect:/admin/merchant";
	}

	@RequestMapping(value = "/delete/{id}")
	public String delete(@PathVariable int id) {

		merchantService.delete(id);

		return "redirect:/admin/merchant";
	}

}
