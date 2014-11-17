package com.dariksoft.bazaar.controller;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.dariksoft.bazaar.domain.Country;
import com.dariksoft.bazaar.service.CountryService;

@Controller
@RequestMapping(value="/admin/country")
public class CountryController {
	
	@Autowired
	CountryService countryService;
	
	@Autowired
	private MessageSource messageSource;
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView home(Locale locale){
		 Map<String, Object> model = new HashMap<String, Object>(); 
		 
		  model.put("countries",  countryService.findAll());  
		  model.put("title", messageSource.getMessage("admin.menu.definitions.countries", null,locale));
		  return new ModelAndView("country/list", model);  	
	}
	
	 @RequestMapping(value = "/add", method = RequestMethod.GET)  
	 public ModelAndView addForm(@ModelAttribute("command")Country country,  
	   BindingResult result, Locale locale) {  
		 
		 ModelAndView mv = new ModelAndView("country/add");
		 mv.addObject("title",  messageSource.getMessage("country.insert.message", null,locale));
		 mv.addObject("country",new Country());
		 mv.addObject("title", "Add country");
		 		 
	  return mv;  
	 }  
	 		 
	 @RequestMapping(value = "/add", method = RequestMethod.POST)  
	 public String add(@ModelAttribute("command")Country country,  
	   BindingResult result, Locale locale) {  
		 Map<String, Object> model = new HashMap<String, Object>(); 
		 if(result.hasErrors()){
			
			 model.put("errors", result.getAllErrors());
			 model.put("title",  messageSource.getMessage("country.update.message", null,locale));
			 return "country/add";
		 }
		 countryService.create(country);
	 	 
		 	return "redirect:/admin/country";  
	 }
	 
	 
		@RequestMapping(value="/update/{id}", method=RequestMethod.GET)
		public ModelAndView editTeamPage(@PathVariable Integer id,Locale locale) {
			ModelAndView modelAndView = new ModelAndView("country/update");
			Country country = countryService.find(id);			
			modelAndView.addObject("country",country);
			modelAndView.addObject("title",  messageSource.getMessage("country.update.message", null,locale));
			return modelAndView;
		}

		@RequestMapping(value="/update/{id}", method=RequestMethod.POST)
		public String edditingTeam(@ModelAttribute("command") Country country, @PathVariable Integer id) {

			countryService.update(country);
			
			return "redirect:/admin/country";
		}
	 
	 
	 @RequestMapping(value = "/delete/{id}")  
	 public String add(@PathVariable int id) {  
		 
		 countryService.delete(id);
	 	 
		 	return "redirect:/admin/country";  
	 }
	 

}
