package com.dariksoft.bazaar.controller;

import java.util.Locale;

import javax.validation.Valid;

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

import com.dariksoft.bazaar.domain.City;
import com.dariksoft.bazaar.domain.Contact;
import com.dariksoft.bazaar.domain.Merchant;
import com.dariksoft.bazaar.domain.Person;
import com.dariksoft.bazaar.propertyeditor.CityEditor;
import com.dariksoft.bazaar.service.CityService;
import com.dariksoft.bazaar.service.MerchantService;

@Controller
@RequestMapping(value = "/admin/merchant")
public class MerchantController {

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
	public String addForm( Locale locale, Model uiModel) {

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
			return "merchant/add";
		}
		merchantService.create(merchant);

		return "redirect:/admin/merchant";
	}

	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public String updateFrom(@PathVariable Integer id, Locale locale,
			Model uiModel) {

		Merchant merchant = merchantService.find(id);
		uiModel.addAttribute("merchant", merchant);
		uiModel.addAttribute("title", messageSource.getMessage(
				"merchant.update.message", null, locale));
		return "merchant/update";
	}

	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	public String update(@ModelAttribute("merchant") @Valid Merchant merchant,
			@PathVariable Integer id, BindingResult result, Locale locale,
			Model uiModel) {

		if (result.hasErrors()) {
			uiModel.addAttribute("title", messageSource.getMessage(
					"merchant.update.message", null, locale));
			return "merchant/update/"+id;
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
