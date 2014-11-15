package com.dariksoft.bazaar.controller;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/")
public class SiteController {
private static final Logger logger = LoggerFactory.getLogger(SiteController.class);
	
	
	@Autowired
	private MessageSource messageSource;
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome to website! The client locale is {}.", locale);
		
		model.addAttribute("title", messageSource.getMessage("website.home.title", null,locale) );
		
		return "website/index";
	}
	
	@RequestMapping(value="/myerror", method = RequestMethod.GET)
	public String myerror() throws Throwable  {
		throw new Exception("This is a sample exception."); 
		
	}
}
