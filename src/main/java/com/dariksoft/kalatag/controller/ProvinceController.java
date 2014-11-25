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

import com.dariksoft.kalatag.domain.Country;
import com.dariksoft.kalatag.domain.Province;
import com.dariksoft.kalatag.propertyeditor.CountryEditor;
import com.dariksoft.kalatag.service.CountryService;
import com.dariksoft.kalatag.service.ProvinceService;

@Controller
@RequestMapping(value = "/admin/province")
public class ProvinceController {
	private static Logger logger = LoggerFactory
			.getLogger(ProvinceController.class);
	@Autowired
	ProvinceService provinceService;

	@Autowired
	CountryService countryService;

	@Autowired
	private MessageSource messageSource;

	private @Autowired CountryEditor countryEditor;

	@InitBinder
	public void iniBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Country.class, this.countryEditor);
	}

	@RequestMapping(method = RequestMethod.GET)
	public String list(Locale locale, Model uiModel) {

		uiModel.addAttribute("provinces", provinceService.findAll());
		uiModel.addAttribute("title", messageSource.getMessage(
				"admin.menu.definitions.provinces", null, locale));
		return "province/list";
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addForm(@ModelAttribute("province") @Valid Province province,
			BindingResult result, Locale locale, Model uiModel) {

		uiModel.addAttribute("title", messageSource.getMessage(
				"province.insert.message", null, locale));
		uiModel.addAttribute("province", new Province());
		uiModel.addAttribute("countries", countryService.findAll());
		
		return "province/add";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(@ModelAttribute("province") @Valid Province province,
			BindingResult result, Locale locale, Model uiModel) {
		if (result.hasErrors()) {
			uiModel.addAttribute("title", messageSource.getMessage(
					"province.update.message", null, locale));
			return "province/add";
		}
		
		logger.debug("country id= "+province.getCountry().getId());
		provinceService.create(province);

		return "redirect:/admin/province";
	}

	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable Integer id, Locale locale,
			Model uiModel) {
	
		Province province = provinceService.find(id);
		uiModel.addAttribute("province", province);
		uiModel.addAttribute("title", messageSource.getMessage(
				"province.update.message", null, locale));
		uiModel.addAttribute("countries", countryService.findAll());
		return "province/update";
	}

	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	public String update(@ModelAttribute("province") @Valid Province province,
			@PathVariable Integer id,
			BindingResult result, Locale locale, Model uiModel) {
		
		if (result.hasErrors()) {
			uiModel.addAttribute("title", messageSource.getMessage(
					"province.update.message", null, locale));
			return "province/update/"+id;
		}
		
		provinceService.update(province);

		return "redirect:/admin/province";
	}

	@RequestMapping(value = "/delete/{id}")
	public String delete(@PathVariable int id) {

		provinceService.delete(id);

		return "redirect:/admin/province";
	}

}
