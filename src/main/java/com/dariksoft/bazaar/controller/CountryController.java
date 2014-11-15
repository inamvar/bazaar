package com.dariksoft.bazaar.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView home(){
		 Map<String, Object> model = new HashMap<String, Object>(); 
		 
		  model.put("countries",  countryService.findAll());  
		  model.put("title", "Countries");
		  return new ModelAndView("country/list", model);  	
	}
	
	 @RequestMapping(value = "/add", method = RequestMethod.GET)  
	 public ModelAndView addForm(@ModelAttribute("command")Country country,  
	   BindingResult result) {  
		 ModelAndView mv = new ModelAndView("country/add");
		 mv.addObject("country",new Country());
		 mv.addObject("title", "Add country");
		 		 
	  return mv;  
	 }  
	 		 
	 @RequestMapping(value = "/add", method = RequestMethod.POST)  
	 public String add(@ModelAttribute("command")Country country,  
	   BindingResult result) {  
		 Map<String, Object> model = new HashMap<String, Object>(); 
		 if(result.hasErrors()){
			
			 model.put("errors", result.getAllErrors());
			 model.put("title", "Add country");
			 return "country/add";
		 }
		 countryService.insert(country);
	 	 
		 	return "redirect:/admin/country";  
	 }
	 
	 @RequestMapping(value = "/delete/{id}")  
	 public String add(@PathVariable int id) {  
		 
		 countryService.delete(id);
	 	 
		 	return "redirect:/admin/country";  
	 }

}
