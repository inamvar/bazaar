package com.dariksoft.bazaar.controller;

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
import org.springframework.web.bind.annotation.RequestParam;

import com.dariksoft.bazaar.domain.City;
import com.dariksoft.bazaar.domain.Country;
import com.dariksoft.bazaar.domain.Province;
import com.dariksoft.bazaar.propertyeditor.CountryEditor;
import com.dariksoft.bazaar.propertyeditor.ProvinceEditor;
import com.dariksoft.bazaar.service.CityService;
import com.dariksoft.bazaar.service.CountryService;
import com.dariksoft.bazaar.service.ProvinceService;

@Controller
@RequestMapping(value = "/admin/city")
public class CityController {
	private static final Logger logger = LoggerFactory
			.getLogger(CityController.class);
	@Autowired
	ProvinceService provinceService;

	@Autowired
	CountryService countryService;

	@Autowired
	CityService cityService;

	@Autowired
	private MessageSource messageSource;

	private @Autowired CountryEditor countryEditor;
	private @Autowired ProvinceEditor provinceEditor;

	@InitBinder
	public void iniBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Country.class, this.countryEditor);
		binder.registerCustomEditor(Province.class, this.provinceEditor);
	}

	@RequestMapping(method = RequestMethod.GET)
	public String list(Locale locale, Model uiModel) {
		uiModel.addAttribute("cities", cityService.findAll());
		uiModel.addAttribute("title", messageSource.getMessage(
				"admin.menu.definitions.cities", null, locale));
		return "city/list";
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addForm(
			@ModelAttribute("city") @Valid City city,
			@RequestParam(value = "province.country.id", required = false) Object country_id,
			Locale locale, Model uiModel) {

		try {
			uiModel.addAttribute("title", messageSource.getMessage(
					"province.insert.message", null, locale));
			city = new City();

			uiModel.addAttribute("countries", countryService.findAll());
			if (country_id != null) {
				Country country = countryService.find(Integer
						.valueOf((String) country_id));
				uiModel.addAttribute("country", country);
				city.setProvince(new Province());
				city.getProvince().setCountry(country);
			}
			uiModel.addAttribute("title", messageSource.getMessage(
					"city.insert.message", null, locale));
			uiModel.addAttribute("city", city);
		} catch (Exception e) {
			logger.error(e.getMessage());

		}
		return "city/add";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(@ModelAttribute("city") @Valid City city,
			BindingResult result, Locale locale, Model uiModel) {

		if (result.hasErrors()) {

			uiModel.addAttribute("title", messageSource.getMessage(
					"city.insert.message", null, locale));
			return "city/add";
		}
		cityService.create(city);

		return "redirect:/admin/city";
	}

	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable Integer id, Locale locale,
			Model uiModel) {

		City city = cityService.find(id);
		uiModel.addAttribute("city", city);
		uiModel.addAttribute("title",
				messageSource.getMessage("city.update.message", null, locale));

		return "city/update";
	}

	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	public String update(@ModelAttribute("city") @Valid City city,
			@PathVariable Integer id, BindingResult result, Locale locale,
			Model uiModel) {
		if (result.hasErrors()) {

			uiModel.addAttribute("title", messageSource.getMessage(
					"city.insert.message", null, locale));
			return "city/update/" + id;
		}
		cityService.update(city);

		return "redirect:/admin/city";
	}

	@RequestMapping(value = "/delete/{id}")
	public String delete(@PathVariable int id) {

		cityService.delete(id);

		return "redirect:/admin/city";
	}

}
