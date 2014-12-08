package com.kalatag.controller;

import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kalatag.domain.Country;
import com.kalatag.service.CountryService;

@Controller
@RequestMapping(value = "/admin/country")
public class CountryController {



	@Autowired
	CountryService countryService;

	@Autowired
	private MessageSource messageSource;

	@RequestMapping(method = RequestMethod.GET)
	public String list(Locale locale, Model uiModel) {

		uiModel.addAttribute("countries", countryService.findAll());
		uiModel.addAttribute("title", messageSource.getMessage(
				"admin.menu.definitions.countries", null, locale));
		return "country/list";
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addForm(@ModelAttribute("country") @Valid Country country,
			BindingResult result, Locale locale, Model uiModel) {

		uiModel.addAttribute("title", messageSource.getMessage(
				"country.insert.message", null, locale));
		uiModel.addAttribute("country", new Country());

		return "country/add";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(@ModelAttribute("country") @Valid Country country,
			BindingResult result, Locale locale, Model uiModel) {
		if (result.hasErrors()) {

			uiModel.addAttribute("title", messageSource.getMessage(
					"country.insert.message", null, locale));
			return "country/add";
		}
		countryService.create(country);

		return "redirect:/admin/country";
	}

	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public String updateFrom(@PathVariable Integer id, Locale locale,
			Model uiModel) {

		Country country = countryService.find(id);
		uiModel.addAttribute("country", country);
		uiModel.addAttribute("title", messageSource.getMessage(
				"country.update.message", null, locale));
		return "country/update";
	}

	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST )
	public String update(@ModelAttribute("country") @Valid Country country,
			@PathVariable Integer id, BindingResult result, Model uiModel,
			Locale locale) {

		if (result.hasErrors()) {

			uiModel.addAttribute("title", messageSource.getMessage(
					"country.update.message", null, locale));
			return "country/update/" + id;
		}

		countryService.update(country);

		return "redirect:/admin/country";
	}

	@RequestMapping(value = "/delete/{id}")
	public String delete(@PathVariable int id) {

		countryService.delete(id);

		return "redirect:/admin/country";
	}

}
