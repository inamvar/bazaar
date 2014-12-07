package com.dariksoft.kalatag.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.dariksoft.kalatag.domain.Person;
import com.dariksoft.kalatag.service.CustomerService;
import com.dariksoft.kalatag.service.person.PersonService;

@Controller
@RequestMapping(value = "/admin/customer")
public class CustomerController {

	@Autowired
	CustomerService customerService;
	@Autowired
	PersonService personService;

	@Autowired
	private MessageSource messageSource;

	@RequestMapping(method = RequestMethod.GET)
	public String list(Locale locale, Model uiModel) {

		uiModel.addAttribute("customers", customerService.findAll());
		uiModel.addAttribute("title", messageSource.getMessage(
				"admin.menu.definitions.customers", null, locale));
		return "customer/list";
	}

	@RequestMapping(value = "/resetpassword", method = RequestMethod.GET)
	public String resetPassword(@RequestParam("email") String email,
			Model uiModel, Locale locale) throws Throwable {

		Person person = personService.findByUserName(email);
		if (person != null && person.getId() > 0) {
			int result = personService.resetPassword(person.getId(), null);
			if (result > 0)
				uiModel.addAttribute("successMsg", messageSource.getMessage(
						"security.resetpass.success.message", null, locale));
		}

		uiModel.addAttribute("customers", customerService.findAll());
		uiModel.addAttribute("title", messageSource.getMessage(
				"admin.menu.definitions.customers", null, locale));

		return "customer/list";

	}

}
