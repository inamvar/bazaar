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
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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
	private static final Logger logger = LoggerFactory.getLogger(CityController.class);
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
	public void iniBinder(WebDataBinder binder){
		binder.registerCustomEditor(Country.class, this.countryEditor);
		binder.registerCustomEditor(Province.class, this.provinceEditor);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView list(Locale locale) {
		

		Map<String, Object> model = new HashMap<String, Object>();

		model.put("cities", cityService.findAll());
		model.put("title", messageSource.getMessage(
				"admin.menu.definitions.cities", null, locale));
		return new ModelAndView("city/list", model);
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView addForm(@ModelAttribute("command") City city,  @RequestParam(value = "province.country.id", required = false) Object country_id,
			BindingResult result, Locale locale) {
	
		ModelAndView mv = new ModelAndView("city/add");
		try{
		mv.addObject("title", messageSource.getMessage(
				"province.insert.message", null, locale));
		city = new City();
		
		
		mv.addObject("countries", countryService.findAll());
		if( country_id != null ){
			Country country  =countryService.find(Integer.valueOf((String)country_id));
		mv.addObject("country",country );
		city.setProvince(new Province());
		city.getProvince().setCountry(country);
		}
				mv.addObject("title",  messageSource.getMessage(
				"city.insert.message", null, locale));
				mv.addObject("city",city);
		}catch(Exception e){
			logger.error(e.getMessage());
			
		}
		return mv;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(@ModelAttribute("command") City city,
			BindingResult result, Locale locale) {
		Map<String, Object> model = new HashMap<String, Object>();
		if (result.hasErrors()) {
			List<ObjectError> errors = result.getAllErrors();
			for(ObjectError error : errors){
				logger.warn(error.toString());
			}
			model.put("errors", errors);
			model.put("title", messageSource.getMessage(
					"city.insert.message", null, locale));
			return "city/add";
		}
		cityService.create(city);

		return "redirect:/admin/city";
	}

	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public ModelAndView updateForm(@PathVariable Integer id, Locale locale) {
		ModelAndView modelAndView = new ModelAndView("city/update");
		City city = cityService.find(id);
		modelAndView.addObject("city", city);
		modelAndView.addObject("title", messageSource.getMessage(
				"city.update.message", null, locale));
	
		return modelAndView;
	}

	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	public String update(@ModelAttribute("command") City city,
			@PathVariable Integer id) {

		cityService.update(city);

		return "redirect:/admin/city";
	}

	@RequestMapping(value = "/delete/{id}")
	public String delete(@PathVariable int id) {

		cityService.delete(id);

		return "redirect:/admin/city";
	}

}
