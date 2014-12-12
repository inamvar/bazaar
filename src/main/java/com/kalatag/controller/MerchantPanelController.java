package com.kalatag.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.kalatag.service.merchant.MerchantService;

@Controller
@RequestMapping(value = "/merchant")
public class MerchantPanelController {
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private MerchantService merchantSrvice;
	
	@RequestMapping(method = RequestMethod.GET)
	public String panel(@RequestParam("id") int id, Locale locale, Model uiModel) {

		uiModel.addAttribute("merchant", merchantSrvice.find(id));
		uiModel.addAttribute("title", messageSource.getMessage(
				"website.merchant.panel.title", null, locale));
		return "website/merchant/home";
	}

	
	
}
