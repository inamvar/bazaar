package com.dariksoft.bazaar.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	
	
	@RequestMapping(value="/",method = RequestMethod.GET)
	public ModelAndView home(){
		 Map<String, Object> model = new HashMap<String, Object>(); 
		 
		  model.put("countries",  countryService.findAll());  
		  return new ModelAndView("country/list", model);  	
	}
	
	 @RequestMapping(value = "/add", method = RequestMethod.GET)  
	 public ModelAndView addForm(@ModelAttribute("command")Country country,  
	   BindingResult result) {  
		 ModelAndView mv = new ModelAndView("country/add");
		 mv.addObject("country",new Country());
		 		 
	  return mv;  
	 }  
	 @RequestMapping(value = "/add", method = RequestMethod.POST)  
	 public ModelAndView add(@ModelAttribute("command")Country country,  
	   BindingResult result) {  
		 
		 if(result.hasErrors()){
			 ModelAndView errorMv = new ModelAndView("country/add");
			 errorMv.addObject("errors", result.getAllErrors());
			 return errorMv;
		 }
		 countryService.insert(country);
		 ModelAndView mv = new ModelAndView("country/list");
		 mv.addObject("message","Successfull");
		 	 
		 		 
	  return mv;  
	 }  

}
